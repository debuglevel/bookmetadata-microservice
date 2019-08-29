package de.debuglevel.bookmetadata.metadataprovider.openlibrary

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class OpenLibraryDataService : DataService() {
    private val logger = KotlinLogging.logger {}

    override fun buildUri(isbn: ISBN): String {
        return UriTemplate.of("https://openlibrary.org/api/books?bibkeys=ISBN:{isbn}&jscmd=data&format=json")
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