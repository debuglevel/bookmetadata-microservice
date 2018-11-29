package de.debuglevel.bookmetadata.rest.books.informationfetcher

object IsbnUtils {
    fun reformat(isbn: String): String {
        return isbn.replace("[^\\d]".toRegex(), "")
    }
}