package de.debuglevel.bookmetadata.metadataprovider.openlibrary

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import java.text.Normalizer
import javax.inject.Singleton

@Singleton
class OpenLibraryDataService(
    @param:Client("https://openlibrary.org/api") private val httpClient: HttpClient
) : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug("Fetching JSON from OpenLibrary Books API for '$isbn'...")

        val uri = UriTemplate.of("https://openlibrary.org/api/books?bibkeys=ISBN:{isbn}&jscmd=data&format=json")
            .expand(
                mapOf(
                    "isbn" to isbn.normalized
                )
            )

        logger.debug { "Requesting $uri ..." }
        val data = httpClient.toBlocking().retrieve(uri).let {
            Normalizer.normalize(it, Normalizer.Form.NFC)
        }

        logger.debug { "Fetched JSON from OpenLibrary Books API for '$isbn'." }
        logger.trace { "Fetched JSON from OpenLibrary Books API for '$isbn': $data" }
        return data
    }
}