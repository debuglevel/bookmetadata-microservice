package de.debuglevel.bookmetadata.rest.books.informationfetcher.openlibrary

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.debuglevel.bookmetadata.rest.books.BookDTO
import de.debuglevel.bookmetadata.rest.books.informationfetcher.BookNotFoundException
import de.debuglevel.bookmetadata.rest.books.informationfetcher.InformationFetcher
import mu.KotlinLogging
import java.net.URL

class OpenLibraryInformationFetcher : InformationFetcher() {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: String): String {
        logger.debug("Fetching JSON from OpenLibrary Books API for $isbn...")

        return URL("https://openlibrary.org/api/books?bibkeys=ISBN:$isbn&jscmd=data&format=json")
                .readText()
    }

    override fun toBook(data: String): BookDTO {
        logger.debug("Converting JSON from OpenLibrary Books API to BookDTO object...")
        logger.debug(data)

        val book = BookDTO(null)

        val jsonObject = Gson().fromJson(data, JsonObject::class.java)

        val mainElement = if (jsonObject?.keySet()?.firstOrNull() != null) jsonObject.getAsJsonObject(jsonObject.keySet()?.first()) else null

        if (mainElement == null || jsonObject?.isJsonNull == true) {
            throw BookNotFoundException()
        }

        book.title = mainElement.getAsJsonPrimitive("title")?.asString

        val subtitle = mainElement.getAsJsonPrimitive("subtitle")?.asString

        if (!subtitle.isNullOrBlank()) {
            book.title += ": $subtitle"
        }

        book.author = mainElement.getAsJsonArray("authors")?.joinToString(separator = ", ", transform = { it.asJsonObject.getAsJsonPrimitive("name").asString })

        book.year = mainElement.getAsJsonPrimitive("publish_date")?.asString

        book.isbn = mainElement.getAsJsonObject("identifiers")
                ?.getAsJsonArray("isbn_13")?.get(0)
                ?.asString

        book.publisher = mainElement.getAsJsonArray("publishers")
                ?.joinToString(separator = ", ", transform = { it.asJsonObject.getAsJsonPrimitive("name").asString })

        return book
    }

}