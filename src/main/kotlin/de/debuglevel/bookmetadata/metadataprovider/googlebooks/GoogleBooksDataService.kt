package de.debuglevel.bookmetadata.metadataprovider.googlebooks

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class GoogleBooksDataService : DataService() {
    private val logger = KotlinLogging.logger {}

    override fun buildUri(isbn: ISBN): String {
        return UriTemplate.of("https://www.googleapis.com/books/v1/volumes?q=isbn:${isbn}")
            .expand(
                mapOf(
                    "isbn" to isbn.normalized
                )
            )
    }

    override fun processData(data: String): String {
        return data
    }
}