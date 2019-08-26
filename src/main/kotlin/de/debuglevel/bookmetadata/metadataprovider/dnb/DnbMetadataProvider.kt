package de.debuglevel.bookmetadata.metadataprovider.dnb

import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.metadataprovider.BookNotFoundException
import de.debuglevel.bookmetadata.metadataprovider.MetadataProvider
import de.debuglevel.bookmetadata.metadataprovider.marc21.MARC21XmlParser
import mu.KotlinLogging
import java.net.URL
import javax.inject.Singleton

@Singleton
class DnbMetadataProvider(
    override val dataService: DnbDataService
) : MetadataProvider() {
    private val logger = KotlinLogging.logger {}

    override val name = "Deutsche Nationalbibliothek"

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