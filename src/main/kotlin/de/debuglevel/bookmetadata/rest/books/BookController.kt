package de.debuglevel.bookmetadata.rest.books

import de.debuglevel.bookmetadata.rest.books.informationfetcher.BookNotFoundException
import de.debuglevel.bookmetadata.rest.books.informationfetcher.FallbackInformationFetcher
import de.debuglevel.bookmetadata.rest.responsetransformer.JsonTransformer
import mu.KotlinLogging
import spark.kotlin.RouteHandler
import java.io.FileNotFoundException
import java.net.ConnectException

object BookController {
    private val logger = KotlinLogging.logger {}

    fun getOne(): RouteHandler.() -> String {
        return {
            val isbn = queryParams("isbn")

            try {
                val book = FallbackInformationFetcher().getBook(isbn)

                type(contentType = "application/json")
                JsonTransformer.render(book)
            } catch (e: BookNotFoundException) {
                logger.info(e) { "Book with ISBN '$isbn' was not found" }
                response.type("application/json")
                response.status(404)
                "{\"message\":\"Book with ISBN '$isbn' was not found.\"}"
            } catch (e: ConnectException) {
                logger.error(e) { "Service was not available." }
                response.type("application/json")
                response.status(502)
                "{\"message\":\"Selected service provider was not available.\"}"
            } catch (e: FileNotFoundException) {
                logger.error(e) { "Service returned 404." }
                response.type("application/json")
                response.status(502)
                "{\"message\":\"Selected service provider implementation may be broken.\"}"
            } catch (e: Exception) {
                logger.error(e) { "Unknown exception occurred." }
                response.type("application/json")
                response.status(500)
                "{\"message\":\"Unknown exception.\"}"
            }
        }
    }
}