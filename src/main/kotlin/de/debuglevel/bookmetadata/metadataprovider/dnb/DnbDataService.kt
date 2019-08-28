package de.debuglevel.bookmetadata.metadataprovider.dnb

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.context.annotation.Property
import mu.KotlinLogging
import java.net.URL
import java.text.Normalizer
import javax.inject.Singleton

@Singleton
class DnbDataService(
    @Property(name = "app.dnb.accesstoken") private val accessToken: String = ""
) : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug { "Fetching XML from DNB SRU for '$isbn'..." }

        val url =
            "http://services.dnb.de/sru/dnb?version=1.1&operation=searchRetrieve&query=isbn%3D${isbn.normalized}&recordSchema=MARC21-xml&accessToken=$accessToken"
        logger.debug { "Requesting $url ..." }
        val xmlData = URL(url).readText().let {
            Normalizer.normalize(it, Normalizer.Form.NFC)
        }

        if (xmlData.contains("info:srw/diagnostic/1/3")) {
            logger.error { "The DNB access token '$accessToken' is invalid:\n$xmlData" }
            throw InvalidAccessTokenException()
        }

        logger.debug { "Fetched XML from DNB SRU for '$isbn'." }
        return xmlData
    }
}