package de.debuglevel.bookmetadata.rest.books.informationfetcher

class BookNotFoundException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}