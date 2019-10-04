package de.debuglevel.bookmetadata.metadataprovider.dnb.marcxml

import de.debuglevel.bookmetadata.NameUtils
import mu.KotlinLogging
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

/**
 * Parses MARCXML records embedded in the DNB SRU XML data
 * Original MARC21 reference: http://www.loc.gov/marc/bibliographic/
 * MARC21 reference for DNB: https://www.dnb.de/DE/Professionell/Metadatendienste/Exportformate/MARC21/marc21_node.html
 */
class SruMarcXmlParser(xmlData: String) {
    private val logger = KotlinLogging.logger {}

    private val xmlDocument = buildXmlDocument(xmlData)
    private val record = 1

    val bookCount: Int
        get() = getXPathValue("/searchRetrieveResponse/numberOfRecords/text()")?.toInt() ?: 0
    val year: String?
        get() = getValue("264", "c")?.stripNonNumerical()
    val pages: String?
        get() = getValue("300", "a")?.stripNonNumerical()
    val title: String?
        get() = getValue("245", "a")
    val author: String?
        get() {
            logger.debug { "Getting author..." }

            // there are various places, in which the authors are available in various formats

            // "MAIN ENTRY--PERSONAL NAME (Haupteintragung - Personenname)"
            // format: "Freud, Sigmund"
            val field100a = getValue("100", "a")
            // "ADDED ENTRY--PERSONAL NAME (Nebeneintragung - Personenname)"
            // format: "Freud, Sigmund"
            // is additional information (if existent) to field 100
            val field700a = getValues("700", "a")
            // "TITLE STATEMENT (Titelangabe); Statement of responsibility, etc. (Verfasserangabe etc.)"
            // some other aggregated information which may contain the author if not present in 100a and 700a
            // format: "Sigmund Freud, Anna Freud"
            val field245c = getValue("245", "c")

            //
            val personalEntries = listOf(field100a)
                .plus(field700a)
                .joinToString("; ")

            val author = if (personalEntries.isNotEmpty()) {
                personalEntries
            } else {
                // may contain authors, but in "Sigmund Freud, Anna Freud" format; has to be converted therefore
                NameUtils.convertToLastnameFirst(field245c)
            }

            logger.debug { "Got author: $author" }
            return author
        }
    val publisher: String?
        get() = getValue("264", "b")
    val place: String?
        get() = getValue("264", "a")
    val edition: String?
        get() = getValue("250", "a")
    val isbn: String?
        get() = getValue("020", "a")
    val series: String?
        get() = getValue("490", "a")
    val volume: String?
        get() {
            // if "v" in "490" is not present, "n" in "245" might be
            return getValue("490", "v") ?: getValue("245", "n")
        }
    //val price: String?
    val subtitle: String?
        get() {
            // sometimes, "b" is missing, but "p" is present
            return getValue("245", "b") ?: getValue("245", "p")
        }
    val tocUrl: String?
        get() = getXPathValue("/searchRetrieveResponse/records/record[$record]/recordData/record/datafield[@tag='856']/subfield[@code=\"3\"][.=\"Inhaltsverzeichnis\"]/../subfield[@code=\"u\"]/text()")
    val abstractUrl: String?
        get() = getXPathValue("/searchRetrieveResponse/records/record[$record]/recordData/record/datafield[@tag='856']/subfield[@code=\"3\"][.=\"Inhaltstext\"]/../subfield[@code=\"u\"]/text()")
    val language: String?
        get() = convertLanguage(getValue("041", "a"))

    private fun convertLanguage(value: String?): String? {
        return when (value) {
            "ger" -> "de"
            "eng" -> "en"
            null -> null
            else -> "unknown"
        }
    }

    private fun getValue(datafieldTag: String, subfieldCode: String): String? {
        logger.debug { "Getting MARC21 value for datafield tag '$datafieldTag', subfield code '$subfieldCode'..." }
        val value =
            getXPathValue("/searchRetrieveResponse/records/record[$record]/recordData/record/datafield[@tag='$datafieldTag']//subfield[@code='$subfieldCode']/text()")
        logger.debug { "Got MARC21 value for datafield tag '$datafieldTag', subfield code '$subfieldCode': '$value'" }
        return value
    }

    private fun getValues(datafieldTag: String, subfieldCode: String): Set<String?> {
        logger.debug { "Getting MARC21 values for datafield tag '$datafieldTag', subfield code '$subfieldCode'..." }
        val values =
            getXPathValues("/searchRetrieveResponse/records/record[$record]/recordData/record/datafield[@tag='$datafieldTag']//subfield[@code='$subfieldCode']/text()")
        logger.debug { "Got MARC21 values for datafield tag '$datafieldTag', subfield code '$subfieldCode': '$values'" }
        return values
    }

    private fun getXPathValue(xpathString: String): String? {
        logger.debug("Getting XPath value for '$xpathString'...")

        // XPath stuff is not thread-safe; creating new instances therefore
        val xpathFactory = XPathFactory.newInstance()
        val xpath = xpathFactory.newXPath()

        val xpathExpression = xpath.compile(xpathString)
        val result = xpathExpression.evaluate(xmlDocument, XPathConstants.NODESET)
        val nodes = result as NodeList

        val content = nodes.item(0)?.textContent

        logger.trace("Got XPath value: '$content'")

        return content
    }

    private fun getXPathValues(xpathString: String): Set<String?> {
        logger.debug("Getting XPath values for '$xpathString'...")

        // XPath stuff is not thread-safe; creating new instances therefore
        val xpathFactory = XPathFactory.newInstance()
        val xpath = xpathFactory.newXPath()

        val xpathExpression = xpath.compile(xpathString)
        val result = xpathExpression.evaluate(xmlDocument, XPathConstants.NODESET)
        val nodes = result as NodeList

        val content = mutableSetOf<String?>()
        for (nodeIndex in 0..(nodes.length - 1)) {
            content.add(nodes.item(nodeIndex)?.textContent)
        }

        logger.trace("Got XPath values: '$content'")

        return content
    }

    private fun String.stripNonNumerical(): String {
        val mixedString = this

        val regex = Regex("[^0-9]")
        val nonNumerical = regex.replace(mixedString, "")

        return nonNumerical
    }

    private fun buildXmlDocument(xml: String): Document {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val inputSource = InputSource(StringReader(xml))
        return builder.parse(inputSource)
    }
}

