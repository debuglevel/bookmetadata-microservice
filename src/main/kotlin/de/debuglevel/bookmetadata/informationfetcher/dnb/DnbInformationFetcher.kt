package de.debuglevel.bookmetadata.informationfetcher.dnb

import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.informationfetcher.BookNotFoundException
import de.debuglevel.bookmetadata.informationfetcher.InformationFetcher
import de.debuglevel.bookmetadata.informationfetcher.marc21.MARC21XmlParser
import mu.KotlinLogging
import java.net.URL

class DnbInformationFetcher(private val accessToken: String) : InformationFetcher() {
    private val logger = KotlinLogging.logger {}

    override val name = "Deutsche Nationalbibliothek"

    override fun fetchData(isbn: String): String {
        logger.debug { "Fetching XML from DNB SRU for '$isbn'..." }

        val url = "http://services.dnb.de/sru/dnb?version=1.1&operation=searchRetrieve&query=isbn%3D$isbn&recordSchema=MARC21-xml&accessToken=$accessToken"
        val xmlData = URL(url).readText()

        if (xmlData.contains("info:srw/diagnostic/1/3")) {
            logger.error { "The DNB access token '$accessToken' is invalid:\n$xmlData" }
            throw InvalidAccessTokenException()
        }

        logger.debug { "Fetched XML from DNB SRU for '$isbn'." }
        return xmlData
    }

    override fun toBook(data: String): BookResponseDTO {
        logger.debug { "Extracting information from DNB SRU XML..." }
        logger.trace { data }

        val marc21XmlParser = MARC21XmlParser(data)

        val book = BookResponseDTO(null)

        val countBooks = marc21XmlParser.bookCount
        if (countBooks == 0) {
            logger.info { "No books found" }
            throw BookNotFoundException()
        }

        book.title = marc21XmlParser.title
        book.subtitle = marc21XmlParser.subtitle
        book.combinedTitle = if (book.subtitle.isNullOrBlank()) book.title else "${book.title}: ${book.subtitle}"
        book.author = marc21XmlParser.author
        book.year = marc21XmlParser.year
        book.publisher = marc21XmlParser.publisher
        book.place = marc21XmlParser.place
        book.edition = marc21XmlParser.edition
        book.isbn = marc21XmlParser.isbn
        book.series = marc21XmlParser.series
        book.volume = marc21XmlParser.volume
        //book.price = marc21XmlParser.price
        book.pages = marc21XmlParser.pages
        book.tableOfContentsUrl = marc21XmlParser.tocUrl
        book.abstractUrl = marc21XmlParser.abstractUrl

        val abstractUrl = book.abstractUrl
        book.abstract = if (abstractUrl != null) getAbstract(abstractUrl) else null

        logger.debug { "Extracted information from DNB SRU XML." }
        logger.trace { "Extracted information from DNB SRU XML: $book" }
        return book
    }

    private fun getAbstract(abstractUrl: String): String? {
        logger.debug { "Getting abstract from '$abstractUrl'..." }
        val response = URL(abstractUrl).readText()

        val responseText = response.strip()

        val regex = """.*<BODY>(.*)</BODY>.*""".toRegex()
        val matchResult = regex.find(responseText)

        val abstractText = matchResult?.groups?.get(1)?.value

        logger.debug { "Got abstract from '$abstractUrl'." }
        logger.debug { "Got abstract from '$abstractUrl': $abstractText" }
        return abstractText
    }

    private fun String.strip(extendedChars: Boolean = false): String {
        val sb = StringBuilder()
        for (c in this) {
            val i = c.toInt()
            if (i in 32..126 || (!extendedChars && i >= 128)) sb.append(c)
        }
        return sb.toString()
    }
}