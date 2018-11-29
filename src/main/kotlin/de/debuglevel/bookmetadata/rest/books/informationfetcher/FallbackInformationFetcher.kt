package de.debuglevel.bookmetadata.rest.books.informationfetcher

import de.debuglevel.bookmetadata.rest.books.BookDTO
import de.debuglevel.bookmetadata.rest.books.informationfetcher.dnb.DnbInformationFetcher
import de.debuglevel.bookmetadata.rest.books.informationfetcher.googlebooks.GoogleBooksInformationFetcher
import de.debuglevel.bookmetadata.rest.books.informationfetcher.openlibrary.OpenLibraryInformationFetcher
import de.debuglevel.bookmetadata.rest.books.informationfetcher.worldcat.WorldCatInformationFetcher
import mu.KotlinLogging

class FallbackInformationFetcher : InformationFetcher() {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: String): String {
        throw NotImplementedError("not implemented on purpose")
    }

    override fun toBook(data: String): BookDTO {
        throw NotImplementedError("not implemented on purpose")
    }

    override fun getBook(isbn: String): BookDTO {
        logger.debug("Getting book with ISBN '$isbn' in FallbackInformationFetcher...")

        val informationFetchers = listOf(
                DnbInformationFetcher(),
                GoogleBooksInformationFetcher(),
                WorldCatInformationFetcher(),
                OpenLibraryInformationFetcher()
        )

        for (informationFetcher in informationFetchers) {
            logger.debug("Try getting book with ISBN '$isbn' from $informationFetcher...")
            val book = try {
                val book = informationFetcher.getBook(isbn)
                logger.debug("Got book with ISBN '$isbn' from $informationFetcher...")
                book.source = informationFetcher::class.simpleName
                book
            } catch (e: Exception) {
                logger.debug(e) { "Failed getting book with ISBN '$isbn' from $informationFetcher" }
                null
            }

            if (book != null) {
                logger.debug("Succeeded getting book with ISBN $isbn from $informationFetcher")
                return book
            }
        }

        logger.info("No book information found for $isbn in any InformationFetcher")

        throw BookNotFoundException("ISBN $isbn not found in any InformationFetcher by ${this.javaClass.name}")
    }
}