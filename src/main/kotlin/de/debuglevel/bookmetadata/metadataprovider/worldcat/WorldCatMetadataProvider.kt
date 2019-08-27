package de.debuglevel.bookmetadata.metadataprovider.worldcat

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.NameUtils
import de.debuglevel.bookmetadata.metadataprovider.BookNotFoundException
import de.debuglevel.bookmetadata.metadataprovider.MetadataProvider
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class WorldCatMetadataProvider(
    override val dataService: WorldCatDataService
) : MetadataProvider() {
    private val logger = KotlinLogging.logger {}

    override val name = "WorldCat xISBN"

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

        val author = jsonObject?.getAsJsonArray("list")?.get(0)
                ?.asJsonObject?.getAsJsonPrimitive("author")?.asString
        book.author = if (author != null) NameUtils.convertToLastnameFirst(author) else null

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