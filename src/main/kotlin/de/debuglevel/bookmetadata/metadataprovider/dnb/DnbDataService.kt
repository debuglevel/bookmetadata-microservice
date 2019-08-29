package de.debuglevel.bookmetadata.metadataprovider.dnb

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.context.annotation.Property
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import java.text.Normalizer
import javax.inject.Singleton

@Singleton
class DnbDataService(
    @Property(name = "app.dnb.accesstoken") private val accessToken: String = "",
    @param:Client("http://services.dnb.de/sru") private val httpClient: HttpClient
) : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug { "Fetching data from DNB SRU for '$isbn'..." }

        val uri =
            UriTemplate.of("http://services.dnb.de/sru/dnb?version=1.1&operation=searchRetrieve&query=isbn%3D{isbn}&recordSchema=MARC21-xml&accessToken={accessToken}")
                .expand(
                    mapOf(
                        "isbn" to isbn.normalized,
                        "accessToken" to accessToken
                    )
                )

        logger.debug { "Requesting $uri ..." }
        val data = httpClient.toBlocking().retrieve(uri).let {
            Normalizer.normalize(it, Normalizer.Form.NFC)
        }

        if (data.contains("info:srw/diagnostic/1/3")) {
            logger.error { "The DNB access token '$accessToken' is invalid:\n$data" }
            throw InvalidAccessTokenException()
        }

        logger.debug { "Fetched data from DNB SRU for '$isbn'." }
        logger.trace { "Fetched data from DNB SRU for '$isbn': $data" }
        return data
    }
}