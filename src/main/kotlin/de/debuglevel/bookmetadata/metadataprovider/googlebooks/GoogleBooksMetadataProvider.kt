package de.debuglevel.bookmetadata.metadataprovider.googlebooks

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
class GoogleBooksMetadataProvider(
    override val dataService: GoogleBooksDataService
) : MetadataProvider() {
    private val logger = KotlinLogging.logger {}

    override val name = "Google Books"

    private final val jsonParser: ParseContext

    init {
        val configuration = Configuration.ConfigurationBuilder()
            .options(Option.DEFAULT_PATH_LEAF_TO_NULL)
            .build()

        jsonParser = JsonPath.using(configuration)
    }

    override fun toBook(data: String): BookResponseDTO {
        logger.debug("Extracting information from Google Books API JSON...")

        val json = jsonParser.parse(data)

        val title = json.read<String>("$.items[0].volumeInfo.title")
        val subtitle = json.read<String>("$.items[0].volumeInfo.subtitle")
        val publishDate = json.read<String>("$.items[0].volumeInfo.publishedDate")
        val year = publishDate?.split("-")?.first()
        val bookCount = json.read<Int>("$.totalItems")
        val authorsFirstnameLastname = json.read<List<String>>("$.items[0].volumeInfo.authors[*]")
        val authorsLastnameFirstname = authorsFirstnameLastname
            .map {
                NameUtils.convertToLastnameFirst(it)
            }
        val authors = authorsLastnameFirstname.joinToString("; ")
        val isbns =
            json.read<List<String>>("$.items[0].volumeInfo.industryIdentifiers[?(@.type == 'ISBN_13')].identifier")
        val isbn = isbns.firstOrNull()
        val publisher = json.read<String>("$.items[0].volumeInfo.publisher")
        val description = json.read<String>("$.items[0].volumeInfo.description")
        val pages = json.read<Int>("$.items[0].volumeInfo.pageCount")
        val language = json.read<String>("$.items[0].volumeInfo.language")
        val textSnippet = json.read<String>("$.items[0].searchInfo.textSnippet")

        val abstract = listOf(description, textSnippet)
            .filter { !it.isNullOrBlank() }
            .joinToString("\n\n")

        val combinedTitle = listOf(title, subtitle)
            .filter { !it.isNullOrBlank() }
            .joinToString(": ")

        if (bookCount == 0) {
            throw BookNotFoundException()
        }

        val book = BookResponseDTO(null).apply {
            this.title = title
            this.subtitle = subtitle
            this.combinedTitle = combinedTitle
            this.author = authors
            this.isbn = isbn

//            this.edition = edition
            this.year = year

            this.publisher = publisher
//            this.place = place

            this.language = language

//            this.volume = volume
//            this.series = series

            this.pages = pages.toString()
//            this.price = price

            this.abstract = abstract
//            this.abstractUrl = abstractUrl

//            this.tableOfContentsUrl = tableOfContentsUrl
        }

        logger.debug("Extracted information from Google Books API JSON: $book")
        return book
    }
}