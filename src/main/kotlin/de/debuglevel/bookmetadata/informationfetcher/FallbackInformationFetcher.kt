package de.debuglevel.bookmetadata.informationfetcher

import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.informationfetcher.dnb.DnbInformationFetcher
import de.debuglevel.bookmetadata.informationfetcher.googlebooks.GoogleBooksInformationFetcher
import de.debuglevel.bookmetadata.informationfetcher.openlibrary.OpenLibraryInformationFetcher
import de.debuglevel.bookmetadata.informationfetcher.worldcat.WorldCatInformationFetcher
import io.micronaut.context.annotation.Property
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class FallbackInformationFetcher(
    @Property(name = "app.dnb.accesstoken") private val dnbAccessToken: String = ""
) : InformationFetcher() {
    private val logger = KotlinLogging.logger {}

    override val name = "Fallback Information Fetcher"

    override fun fetchData(isbn: String): String {
        throw NotImplementedError("not implemented on purpose")
    }

    override fun toBook(data: String): BookResponseDTO {
        throw NotImplementedError("not implemented on purpose")
    }

    override fun getBook(isbn: String): BookResponseDTO {
        logger.debug("Getting book with ISBN '$isbn' from '${this.name}'...")

        val informationFetchers = listOf(
            DnbInformationFetcher(dnbAccessToken),
                GoogleBooksInformationFetcher(),
                WorldCatInformationFetcher(),
                OpenLibraryInformationFetcher()
        )

        for (informationFetcher in informationFetchers) {
            try {
                val book = informationFetcher.getBook(isbn)
                book.source = informationFetcher.name

                logger.debug("Succeeded getting book with ISBN $isbn from '${informationFetcher.name}': $book")
                return book
            } catch (e: Exception) {
                logger.debug(e) { "Failed getting book with ISBN '$isbn' from '${informationFetcher.name}'." }
                continue
            }
        }

        logger.info("No book information found for '$isbn' in any InformationFetcher")

        throw BookNotFoundException("ISBN $isbn not found in any upstream service provider")
    }
}