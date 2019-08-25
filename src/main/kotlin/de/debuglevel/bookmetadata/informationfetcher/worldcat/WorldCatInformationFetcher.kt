package de.debuglevel.bookmetadata.informationfetcher.worldcat

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.informationfetcher.BookNotFoundException
import de.debuglevel.bookmetadata.informationfetcher.InformationFetcher
import mu.KotlinLogging
import java.net.URL

class WorldCatInformationFetcher : InformationFetcher() {
    private val logger = KotlinLogging.logger {}

    override val name = "WorldCat xISBN"

    override fun fetchData(isbn: String): String {
        logger.debug("Fetching JSON from WorldCat xISBN for '$isbn'...")

        val json = URL("http://xisbn.worldcat.org/webservices/xid/isbn/$isbn?method=getMetadata&format=json&fl=*")
            .readText()

        logger.debug { "Fetched JSON from WorldCat xISBN for '$isbn'." }
        logger.trace { "Fetched JSON from WorldCat xISBN for '$isbn': $json" }
        return json
    }

    override fun toBook(data: String): BookResponseDTO {
        logger.debug("Extracting information from WorldCat xISBN JSON...")

        val book = BookResponseDTO(null)

        val jsonObject = Gson().fromJson(data, JsonObject::class.java)

        when (jsonObject?.getAsJsonPrimitive("stat")?.asString) {
            "invalidId" -> throw BookNotFoundException()
            "unknownId" -> throw BookNotFoundException()
        }

        book.title = jsonObject?.getAsJsonArray("list")?.get(0)
                ?.asJsonObject?.getAsJsonPrimitive("title")?.asString

        book.author = jsonObject?.getAsJsonArray("list")?.get(0)
                ?.asJsonObject?.getAsJsonPrimitive("author")?.asString

        book.year = jsonObject?.getAsJsonArray("list")?.get(0)
                ?.asJsonObject?.getAsJsonPrimitive("year")?.asString

        // there might be multiple in xISBN?
        book.isbn = jsonObject?.getAsJsonArray("list")?.get(0)
                ?.asJsonObject?.getAsJsonArray("isbn")
                //?.get(0)?.asString
                ?.asString

        book.publisher = jsonObject?.getAsJsonArray("list")?.get(0)
                ?.asJsonObject?.getAsJsonPrimitive("publisher")?.asString

        book.edition = jsonObject?.getAsJsonArray("list")?.get(0)
                ?.asJsonObject?.getAsJsonPrimitive("ed")?.asString

        logger.debug("Extracted information from WorldCat xISBN JSON: $book")
        return book
    }
}