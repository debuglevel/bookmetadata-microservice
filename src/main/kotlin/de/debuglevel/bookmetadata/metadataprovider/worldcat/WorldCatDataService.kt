package de.debuglevel.bookmetadata.metadataprovider.worldcat

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class WorldCatDataService : DataService() {
    private val logger = KotlinLogging.logger {}

    override fun buildUri(isbn: ISBN): String {
        return UriTemplate.of("http://xisbn.worldcat.org/webservices/xid/isbn/{isbn}?method=getMetadata&format=json&fl=*")
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