package de.debuglevel.bookmetadata.metadataprovider.worldcat

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import java.text.Normalizer
import javax.inject.Singleton

@Singleton
class WorldCatDataService(
    @param:Client("http://xisbn.worldcat.org/webservices/xid/isbn") private val httpClient: HttpClient
) : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug("Fetching data from WorldCat xISBN for '$isbn'...")

        val uri =
            UriTemplate.of("http://xisbn.worldcat.org/webservices/xid/isbn/{$}isbn}?method=getMetadata&format=json&fl=*")
                .expand(
                    mapOf(
                        "isbn" to isbn.normalized
                    )
                )

        logger.debug { "Requesting $uri ..." }
        val data = httpClient.toBlocking().retrieve(uri).let {
            Normalizer.normalize(it, Normalizer.Form.NFC)
        }

        logger.debug { "Fetched data from WorldCat xISBN for '$isbn'." }
        logger.trace { "Fetched data from WorldCat xISBN for '$isbn': $data" }
        return data
    }
}