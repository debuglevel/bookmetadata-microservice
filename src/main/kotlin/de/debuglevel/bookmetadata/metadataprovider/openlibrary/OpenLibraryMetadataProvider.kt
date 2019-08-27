package de.debuglevel.bookmetadata.metadataprovider.openlibrary

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.NameUtils
import de.debuglevel.bookmetadata.metadataprovider.BookNotFoundException
import de.debuglevel.bookmetadata.metadataprovider.MetadataProvider
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class OpenLibraryMetadataProvider(
    override val dataService: OpenLibraryDataService
) : MetadataProvider() {
    private val logger = KotlinLogging.logger {}

    override val name = "OpenLibrary"

    override fun toBook(data: String): BookResponseDTO {
        logger.debug("Extracting information from OpenLibrary Books API JSON...")

        val book = BookResponseDTO(null)

        // TODO: maybe use Jackson instead of GSON because it's already included in dependencies
        val jsonObject = Gson().fromJson(data, JsonObject::class.java)

        val mainElement =
            if (jsonObject?.keySet()?.firstOrNull() != null) jsonObject.getAsJsonObject(jsonObject.keySet()?.first()) else null

        if (mainElement == null || jsonObject?.isJsonNull == true) {
            throw BookNotFoundException()
        }

        book.title = mainElement.getAsJsonPrimitive("title")?.asString

        val subtitle = mainElement.getAsJsonPrimitive("subtitle")?.asString

        if (!subtitle.isNullOrBlank()) {
            book.combinedTitle += ": $subtitle"
        } else {
            book.combinedTitle = book.title
        }

        book.author = mainElement.getAsJsonArray("authors")
            ?.joinToString(separator = "; ",
                transform = {
                    val name = it.asJsonObject.getAsJsonPrimitive("name").asString
                    val convertedName = NameUtils.convertToLastnameFirst(name)
                    convertedName
                }
            )

        book.year = mainElement.getAsJsonPrimitive("publish_date")?.asString

        book.isbn = mainElement.getAsJsonObject("identifiers")
            ?.getAsJsonArray("isbn_13")?.get(0)
            ?.asString

        book.place = mainElement.getAsJsonArray("publish_places")
            ?.get(0)
            ?.asJsonObject
            ?.getAsJsonPrimitive("name")
            ?.asString

        book.publisher = mainElement.getAsJsonArray("publishers")
            ?.joinToString(separator = ", ", transform = { it.asJsonObject.getAsJsonPrimitive("name").asString })

        book.pages = mainElement.getAsJsonPrimitive("number_of_pages")?.asString

        logger.debug("Extracted information from OpenLibrary Books API JSON: $book")
        return book
    }
}