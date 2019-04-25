package de.debuglevel.bookmetadata.rest.books.informationfetcher

import de.debuglevel.bookmetadata.rest.books.BookDTO

abstract class InformationFetcher {
    open fun getBook(isbn: String): BookDTO {
        println("Getting book for ISBN $isbn...")

        val reformattedIsbn = IsbnUtils.reformat(isbn)
        val book = toBook(fetchData(reformattedIsbn))
        book.isbn = if (book.isbn.isNullOrBlank()) reformattedIsbn else book.isbn

        return book
    }

    abstract fun fetchData(isbn: String): String

    protected abstract fun toBook(data: String): BookDTO
}