package de.debuglevel.bookmetadata.metadataprovider

import de.debuglevel.bookmetadata.BookResponseDTO

abstract class MetadataProvider {
    abstract val name: String

    protected abstract val dataService: DataService

    open fun getBook(isbn: ISBN): BookResponseDTO {
        println("Getting book for ISBN '$isbn' from '${this.name}'...")

        val data = dataService.fetchData(isbn)
        val book = toBook(data)
        book.requestedIsbn = isbn.toString()

        println("Got book for ISBN '$isbn' from '${this.name}': $book")
        return book
    }

    protected abstract fun toBook(data: String): BookResponseDTO
}