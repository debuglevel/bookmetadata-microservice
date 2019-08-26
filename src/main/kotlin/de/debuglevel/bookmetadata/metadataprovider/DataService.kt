package de.debuglevel.bookmetadata.metadataprovider

interface DataService {
    fun fetchData(isbn: ISBN): String
}