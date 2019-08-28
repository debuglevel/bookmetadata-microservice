package de.debuglevel.bookmetadata.metadataprovider.dnb.marcxml

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

    private val xmlDocument: Document
    private val record = 1

    val bookCount: Int
    val year: String?
    val pages: String?
    val title: String?
    val author: String?
    val publisher: String?
    val place: String?
    val edition: String?
    val isbn: String?
    val series: String?
    val volume: String?
    //val price: String?
    val subtitle: String?
    val tocUrl: String?
    val abstractUrl: String?
    val language: String?

    init {
        xmlDocument = buildXmlDocument(xmlData)

        bookCount = getXPathValue("/searchRetrieveResponse/numberOfRecords/text()")?.toInt() ?: 0
        year = getValue("264", "c")?.stripNonNumerical()
        pages = getValue("300", "a")?.stripNonNumerical()
        title = getValue("245", "a")
        author = getValue("100", "a") ?: getValue("700", "a")
        publisher = getValue("264", "b")
        place = getValue("264", "a")
        edition = getValue("250", "a")
        isbn = getValue("020", "a")
        series = getValue("490", "a")
        language = convertLanguage(getValue("041", "a"))

        // if "v" in "490" is not present, "n" in "245" might be
        volume = getValue("490", "v") ?: getValue("245", "n")
        //price = getValue("020", "c")

        // sometimes, "b" is missing, but "p" is present
        subtitle = getValue("245", "b") ?: getValue("245", "p")

        // both specific to DNB; but can remain here as long as this parser is tied to DNB anyway
        tocUrl =
            getXPathValue("/searchRetrieveResponse/records/record[$record]/recordData/record/datafield[@tag='856']/subfield[@code=\"3\"][.=\"Inhaltsverzeichnis\"]/../subfield[@code=\"u\"]/text()")
        abstractUrl =
            getXPathValue("/searchRetrieveResponse/records/record[$record]/recordData/record/datafield[@tag='856']/subfield[@code=\"3\"][.=\"Inhaltstext\"]/../subfield[@code=\"u\"]/text()")
    }

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

