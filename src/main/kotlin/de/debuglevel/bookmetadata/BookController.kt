package de.debuglevel.bookmetadata

import de.debuglevel.bookmetadata.metadataprovider.BookNotFoundException
import de.debuglevel.bookmetadata.metadataprovider.FallbackMetadataProvider
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import mu.KotlinLogging
import java.io.FileNotFoundException
import java.net.ConnectException

@Controller("/books")
class BookController(private val fallbackInformationFetcher: FallbackMetadataProvider) {
    private val logger = KotlinLogging.logger {}

    @Get("/{isbn}")
    fun getOne(isbn: String): HttpResponse<BookResponseDTO> {
        logger.debug("Called getOne($isbn)")

        return try {
            val book = fallbackInformationFetcher.getBook(ISBN(isbn))
            HttpResponse.ok(book)
        } catch (e: BookNotFoundException) {
            val bookResponseDTO = BookResponseDTO(isbn)
                .apply { error = "Book with ISBN '$isbn' was not found." }

            HttpResponse.notFound(bookResponseDTO)
        } catch (e: ConnectException) {
            val bookResponseDTO = BookResponseDTO(isbn)
                .apply { error = "Upstream service provider was not available." }

            HttpResponse.serverError(bookResponseDTO)
                .status(HttpStatus.BAD_GATEWAY)
        } catch (e: FileNotFoundException) {
            val bookResponseDTO = BookResponseDTO(isbn)
                .apply { error = "Upstream service provider implementation may be broken." }

            HttpResponse.serverError(bookResponseDTO)
        } catch (e: Exception) {
            val bookResponseDTO = BookResponseDTO(isbn)
                .apply { error = "Unknown error occured." }

            HttpResponse.serverError(bookResponseDTO)
        }
    }
}