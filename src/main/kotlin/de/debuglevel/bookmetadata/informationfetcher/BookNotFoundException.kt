package de.debuglevel.bookmetadata.informationfetcher

class BookNotFoundException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}