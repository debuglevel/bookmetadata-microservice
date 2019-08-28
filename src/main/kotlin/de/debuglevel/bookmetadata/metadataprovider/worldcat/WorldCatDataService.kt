package de.debuglevel.bookmetadata.metadataprovider.worldcat

import de.debuglevel.bookmetadata.metadataprovider.DataService
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import mu.KotlinLogging
import java.net.URL
import java.text.Normalizer
import javax.inject.Singleton

@Singleton
class WorldCatDataService : DataService {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: ISBN): String {
        logger.debug("Fetching JSON from WorldCat xISBN for '$isbn'...")

        val url = "http://xisbn.worldcat.org/webservices/xid/isbn/$isbn?method=getMetadata&format=json&fl=*"
        logger.debug { "Requesting $url ..." }
        val json = URL(url).readText().let {
            Normalizer.normalize(it, Normalizer.Form.NFC)
        }

        logger.debug { "Fetched JSON from WorldCat xISBN for '$isbn'." }
        logger.trace { "Fetched JSON from WorldCat xISBN for '$isbn': $json" }
        return json
    }
}