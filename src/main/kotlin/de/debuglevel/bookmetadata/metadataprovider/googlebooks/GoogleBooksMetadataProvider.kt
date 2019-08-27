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
            book.combinedTitle += ": $subtitle"
        } else {
            book.combinedTitle = book.title
        }

        val author = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.getAsJsonArray("authors")
                ?.joinToString(separator = ", ", transform = { it.asJsonPrimitive.asString })
        book.author = if (author != null) NameUtils.convertToLastnameFirst(author) else null

        val date = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("publishedDate")?.asString
        // convert "2017-12-24" to "2017"
        book.year = date?.split("-")?.first()

        // sometimes missing
        book.isbn = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
            ?.get("industryIdentifiers")
            ?.asJsonArray?.firstOrNull { it?.asJsonObject?.get("type")?.asString == "ISBN_13" }
                ?.asJsonObject?.get("identifier")?.asString

        book.publisher = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("publisher")?.asString

        val description = jsonObject?.getAsJsonArray("items")?.get(0)
                ?.asJsonObject?.getAsJsonObject("volumeInfo")
                ?.get("description")?.asString

        book.pages = jsonObject?.getAsJsonArray("items")?.get(0)
            ?.asJsonObject?.getAsJsonObject("volumeInfo")
            ?.get("pageCount")?.asString

        book.language = jsonObject?.getAsJsonArray("items")?.get(0)
            ?.asJsonObject?.getAsJsonObject("volumeInfo")
            ?.get("language")?.asString

        val textSnippet = jsonObject?.getAsJsonArray("items")?.get(0)
            ?.asJsonObject?.getAsJsonObject("searchInfo")
            ?.get("textSnippet")?.asString

        book.abstract = listOf(description, textSnippet)
            .filter { !it.isNullOrBlank() }
            .joinToString("\n\n")

        logger.debug("Extracted information from Google Books API JSON: $book")
        return book
    }
}