package de.debuglevel.bookmetadata.metadataprovider

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import mu.KotlinLogging
import java.text.Normalizer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class DataService {
    private val logger = KotlinLogging.logger {}

    @Inject
    @field:Client("http://placeholder.invalid")
    lateinit var httpClient: HttpClient

    abstract fun buildUri(isbn: ISBN): String

    abstract fun processData(data: String): String

    fun fetchData(isbn: ISBN): String {
        logger.debug { "Fetching data for '$isbn'..." }

        val uri = buildUri(isbn)

        logger.debug { "Requesting $uri ..." }
        val data = httpClient.toBlocking().retrieve(uri).let {
            Normalizer.normalize(it, Normalizer.Form.NFC)
        }

        logger.debug { "Fetched data for '$isbn'." }
        logger.trace { "Fetched data for '$isbn': $data" }

        logger.debug { "Processing data (if implemented by individual DataService)..." }
        val processesData = processData(data)
        logger.debug { "Processed data (if implemented by individual DataService)" }
        logger.trace { "Processed data (if implemented by individual DataService): $processesData" }

        return processesData
    }


}