package de.debuglevel.bookmetadata.informationfetcher

import de.debuglevel.bookmetadata.BookResponseDTO

abstract class InformationFetcher {
    open fun getBook(isbn: String): BookResponseDTO {
        println("Getting book for ISBN '$isbn' from '${this.name}'...")

        val reformattedIsbn = IsbnUtils.reformat(isbn)
        val book = toBook(fetchData(reformattedIsbn))
        book.requestedIsbn = reformattedIsbn

        println("Got book for ISBN '$isbn' from '${this.name}': $book")
        return book
    }

    abstract fun fetchData(isbn: String): String

    protected abstract fun toBook(data: String): BookResponseDTO

    abstract val name: String
}