package de.debuglevel.bookmetadata.metadataprovider.dnb

import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.metadataprovider.BookNotFoundException
import de.debuglevel.bookmetadata.metadataprovider.MetadataProvider
import de.debuglevel.bookmetadata.metadataprovider.dnb.marcxml.SruMarcXmlParser
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

        val parser = SruMarcXmlParser(data)

        val countBooks = parser.bookCount
        if (countBooks == 0) {
            logger.info { "No books found" }
            throw BookNotFoundException()
        }

        val book = BookResponseDTO(null).apply {
            title = parser.title
            subtitle = parser.subtitle
            combinedTitle = when {
                subtitle.isNullOrBlank() -> title
                else -> "${title}: $subtitle"
            }
            author = parser.author
            year = parser.year
            publisher = parser.publisher
            place = parser.place
            edition = parser.edition
            isbn = parser.isbn
            series = parser.series
            volume = parser.volume
            //price = parser.price
            pages = parser.pages
            tableOfContentsUrl = parser.tocUrl
            abstractUrl = parser.abstractUrl
            abstract = abstractUrl?.let { getAbstract(it) }
            language = parser.language
        }

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