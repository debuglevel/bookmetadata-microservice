package de.debuglevel.bookmetadata.informationfetcher.googlebooks

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.informationfetcher.BookNotFoundException
import de.debuglevel.bookmetadata.informationfetcher.InformationFetcher
import mu.KotlinLogging
import java.net.URL

class GoogleBooksInformationFetcher : InformationFetcher() {
    private val logger = KotlinLogging.logger {}

    override val name = "Google Books"

    override fun fetchData(isbn: String): String {
        logger.debug { "Fetching JSON from Google Books API for '$isbn'..." }

        val json = URL("https://www.googleapis.com/books/v1/volumes?q=isbn:$isbn")
            .readText()

        logger.debug { "Fetched JSON from Google Books API for '$isbn'." }
        logger.trace { "Fetched JSON from Google Books API for '$isbn': $json" }
        return json
    }

    override fun toBook(json: String): BookResponseDTO {
        logger.debug("Extracting information from Google Books API JSON...")

        val book = BookResponseDTO(null)

        // TODO: maybe use Jackson instead of GSON because it's already included in dependencies
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)

        val bookCount = jsonObject?.getAsJsonPrimitive("totalItems")?.asInt ?: 0
        if (bookCount == 0) {
            throw BookNotFoundException()
        }

        book.title = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("title")?.asString

        val subtitle = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("subtitle")?.asString

        if (!subtitle.isNullOrBlank()) {
            book.title += ": $subtitle"
        }

        book.author = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.getAsJsonArray("authors")
                ?.joinToString(separator = ", ", transform = { it.asJsonPrimitive.asString })

        book.year = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("publishedDate")?.asString

        // TODO: sometimes (maybe always by now?) missing
        book.isbn = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("industryIdentifiers")?.asJsonArray?.first { it?.asJsonObject?.get("type")?.asString == "ISBN_13" }
                ?.asJsonObject?.get("identifier")?.asString

        book.publisher = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("publisher")?.asString

        book.description = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("description")?.asString

        logger.debug("Extracted information from Google Books API JSON: $book")
        return book
    }
}