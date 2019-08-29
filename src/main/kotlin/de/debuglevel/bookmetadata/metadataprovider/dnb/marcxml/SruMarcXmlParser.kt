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
            // there are various places, in which the authors are available in various formats
            return getValue("100", "a")
                ?: getValue("700", "a")
                ?: NameUtils.convertToLastnameFirst(getValue("245", "c"))
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
        return getXPathValue("/searchRetrieveResponse/records/record[$record]/recordData/record/datafield[@tag='$datafieldTag']//subfield[@code='$subfieldCode']/text()")
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

        logger.trace("Got XPath value '$content'")

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

