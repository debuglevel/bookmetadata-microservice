package de.debuglevel.bookmetadata.metadataprovider.dnb

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.context.annotation.Property
import io.micronaut.http.uri.UriTemplate
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class DnbDataService(
    @Property(name = "app.dnb.accesstoken") private val accessToken: String = ""
) : DataService() {
    private val logger = KotlinLogging.logger {}

    override fun buildUri(isbn: ISBN): String {
        return UriTemplate.of("http://services.dnb.de/sru/dnb?version=1.1&operation=searchRetrieve&query=isbn%3D{isbn}&recordSchema=MARC21-xml&accessToken={accessToken}")
            .expand(
                mapOf(
                    "isbn" to isbn.normalized,
                    "accessToken" to accessToken
                )
            )
    }

    override fun processData(data: String): String {
        if (data.contains("info:srw/diagnostic/1/3")) {
            logger.error { "The DNB access token '$accessToken' is invalid:\n$data" }
            throw InvalidAccessTokenException()
        }

        return data
    }
}