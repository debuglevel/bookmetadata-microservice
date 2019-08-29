package de.debuglevel.bookmetadata.metadataprovider.googlebooks

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import java.text.Normalizer
import javax.inject.Singleton

@Singleton
class GoogleBooksDataService(
    @param:Client("https://www.googleapis.com/books/v1") private val httpClient: HttpClient
) : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug { "Fetching data from Google Books API for '$isbn'..." }

        val uri = UriTemplate.of("https://www.googleapis.com/books/v1/volumes?q=isbn:${isbn}")
            .expand(
                mapOf(
                    "isbn" to isbn.normalized
                )
            )

        logger.debug { "Requesting $uri ..." }
        val data = httpClient.toBlocking().retrieve(uri).let {
            Normalizer.normalize(it, Normalizer.Form.NFC)
        }

        logger.debug { "Fetched data from Google Books API for '$isbn'." }
        logger.trace { "Fetched data from Google Books API for '$isbn': $data" }
        return data
    }
}