package de.debuglevel.bookmetadata.metadataprovider.googlebooks

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import mu.KotlinLogging
import java.net.URL
import javax.inject.Singleton

@Singleton
class GoogleBooksDataService : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug { "Fetching JSON from Google Books API for '$isbn'..." }

        val url = "https://www.googleapis.com/books/v1/volumes?q=isbn:${isbn.normalized}"
        logger.debug { "Requesting $url ..." }
        val json = URL(url).readText()

        logger.debug { "Fetched JSON from Google Books API for '$isbn'." }
        logger.trace { "Fetched JSON from Google Books API for '$isbn': $json" }
        return json
    }
}