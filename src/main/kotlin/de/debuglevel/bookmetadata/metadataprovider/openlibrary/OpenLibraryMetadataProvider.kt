package de.debuglevel.bookmetadata.metadataprovider.openlibrary

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import com.jayway.jsonpath.Option
import com.jayway.jsonpath.ParseContext
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

    private final val jsonParser: ParseContext

    init {
        val configuration = Configuration.ConfigurationBuilder()
            .options(Option.DEFAULT_PATH_LEAF_TO_NULL)
            .build()

        jsonParser = JsonPath.using(configuration)
    }

    override fun toBook(data: String): BookResponseDTO {
        logger.debug("Extracting information from OpenLibrary Books API JSON...")

        // OpenLibrary uses a ridiculous format where the key contains a value: { "ISBN:9783551551672": { ... }}"
        // this extracts the value of this key and converts it back to JSON for JsonPath processing
        val mainElementJson = run {
            // TODO: maybe use Jackson instead of GSON because it's already included in dependencies
            val jsonObject = Gson().fromJson(data, JsonObject::class.java)
            val mainElement =
                if (jsonObject?.keySet()?.firstOrNull() != null) {
                    jsonObject.getAsJsonObject(jsonObject.keySet()?.first())
                } else {
                    null
                }
            if (mainElement == null || jsonObject?.isJsonNull == true) {
                throw BookNotFoundException()
            }
            mainElement.toString()
        }

        val json = jsonParser.parse(mainElementJson)

        val title = json.read<String>("$.title")
        val subtitle = json.read<String>("$.subtitle")
        val year = json.read<String>("$.publish_date")
        val authorsFirstnameLastname = json.read<List<String>>("$.authors[*].name")
        val authorsLastnameFirstname = authorsFirstnameLastname
            .map {
                NameUtils.convertToLastnameFirst(it)
            }
        val authors = authorsLastnameFirstname.joinToString("; ")
        val isbn = json.read<String>("$.identifiers.isbn_13[0]")
        val publishers = json.read<List<String>>("$.publishers[*].name")
        val publisher = publishers.joinToString(", ")
        val place = json.read<String>("$.publish_places[0].name")
        val pages = json.read<Int>("$.number_of_pages")

        val combinedTitle = listOf(title, subtitle)
            .filter { !it.isNullOrBlank() }
            .joinToString(": ")

        val book = BookResponseDTO(null).apply {
            this.title = title
            this.subtitle = subtitle
            this.combinedTitle = combinedTitle
            this.author = authors
            this.isbn = isbn

//            this.edition = edition
            this.year = year

            this.publisher = publisher
            this.place = place

//            this.language = language

//            this.volume = volume
//            this.series = series

            this.pages = pages.toString()
//            this.price = price

//            this.abstract = abstract
//            this.abstractUrl = abstractUrl

//            this.tableOfContentsUrl = tableOfContentsUrl
        }

        logger.debug("Extracted information from OpenLibrary Books API JSON: $book")
        return book
    }
}