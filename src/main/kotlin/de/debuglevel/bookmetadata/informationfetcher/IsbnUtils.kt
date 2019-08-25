package de.debuglevel.bookmetadata.informationfetcher

object IsbnUtils {
    fun reformat(isbn: String): String {
        return isbn.replace("[^\\d]".toRegex(), "")
    }
}