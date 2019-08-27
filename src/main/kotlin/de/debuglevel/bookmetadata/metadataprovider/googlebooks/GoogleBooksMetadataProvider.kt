package de.debuglevel.bookmetadata.metadataprovider.googlebooks

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.NameUtils
import de.debuglevel.bookmetadata.metadataprovider.BookNotFoundException
import de.debuglevel.bookmetadata.metadataprovider.MetadataProvider
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class GoogleBooksMetadataProvider(
    override val dataService: GoogleBooksDataService
) : MetadataProvider() {
    private val logger = KotlinLogging.logger {}

    override val name = "Google Books"

    override fun toBook(data: String): BookResponseDTO {
        logger.debug("Extracting information from Google Books API JSON...")

        val book = BookResponseDTO(null)

        // TODO: maybe use Jackson instead of GSON because it's already included in dependencies
        val jsonObject = Gson().fromJson(data, JsonObject::class.java)

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

        val author = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.getAsJsonArray("authors")
                ?.joinToString(separator = ", ", transform = { it.asJsonPrimitive.asString })
        book.author = if (author != null) NameUtils.convertToLastnameFirst(author) else null

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