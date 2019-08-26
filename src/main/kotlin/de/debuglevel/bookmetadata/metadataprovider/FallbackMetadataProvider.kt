package de.debuglevel.bookmetadata.metadataprovider

import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.metadataprovider.dnb.DnbMetadataProvider
import de.debuglevel.bookmetadata.metadataprovider.googlebooks.GoogleBooksMetadataProvider
import de.debuglevel.bookmetadata.metadataprovider.openlibrary.OpenLibraryMetadataProvider
import de.debuglevel.bookmetadata.metadataprovider.worldcat.WorldCatMetadataProvider
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class FallbackMetadataProvider(
    private val dnbInformationFetcher: DnbMetadataProvider,
    private val googleBooksInformationFetcher: GoogleBooksMetadataProvider,
    private val worldCatInformationFetcher: WorldCatMetadataProvider,
    private val openLibraryInformationFetcher: OpenLibraryMetadataProvider
) : MetadataProvider() {
    private val logger = KotlinLogging.logger {}

    override val name = "Fallback Information Fetcher"

    override val dataService: DataService
        get() = TODO("not implemented on purpose")

    override fun toBook(data: String): BookResponseDTO {
        throw NotImplementedError("not implemented on purpose")
    }

    override fun getBook(isbn: ISBN): BookResponseDTO {
        logger.debug("Getting book with ISBN '$isbn' from '${this.name}'...")

        val informationFetchers = listOf(
            dnbInformationFetcher,
            googleBooksInformationFetcher,
            worldCatInformationFetcher,
            openLibraryInformationFetcher
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