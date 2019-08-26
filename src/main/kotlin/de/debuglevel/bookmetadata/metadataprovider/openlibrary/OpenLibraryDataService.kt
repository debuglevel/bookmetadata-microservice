package de.debuglevel.bookmetadata.metadataprovider.openlibrary

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import mu.KotlinLogging
import java.net.URL
import javax.inject.Singleton

@Singleton
class OpenLibraryDataService : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug("Fetching JSON from OpenLibrary Books API for '$isbn'...")

        val url = "https://openlibrary.org/api/books?bibkeys=ISBN:${isbn.normalized}&jscmd=data&format=json"
        logger.debug { "Requesting $url ..." }
        val json = URL(url).readText()

        logger.debug { "Fetched JSON from OpenLibrary Books API for '$isbn'." }
        logger.trace { "Fetched JSON from OpenLibrary Books API for '$isbn': $json" }
        return json
    }
}