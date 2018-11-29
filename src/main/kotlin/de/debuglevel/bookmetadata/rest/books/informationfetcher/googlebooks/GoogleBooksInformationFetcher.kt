package de.debuglevel.bookmetadata.rest.books.informationfetcher.googlebooks

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.debuglevel.bookmetadata.rest.books.BookDTO
import de.debuglevel.bookmetadata.rest.books.informationfetcher.BookNotFoundException
import de.debuglevel.bookmetadata.rest.books.informationfetcher.InformationFetcher
import mu.KotlinLogging
import java.net.URL

class GoogleBooksInformationFetcher : InformationFetcher() {
    private val logger = KotlinLogging.logger {}

    override fun fetchData(isbn: String): String {
        logger.debug("Fetching JSON from Google Books API for $isbn...")

        return URL("https://www.googleapis.com/books/v1/volumes?q=isbn:$isbn")
                .readText()
    }

    override fun toBook(data: String): BookDTO {
        logger.debug("Converting JSON from Google Books API to BookDTO object...")
        logger.debug(data)

        val book = BookDTO(null)

        val jsonObject = Gson().fromJson(data, JsonObject::class.java)

        val countBooks = jsonObject?.getAsJsonPrimitive("totalItems")?.asInt ?: 0
        if (countBooks == 0) {
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

        return book
    }
}