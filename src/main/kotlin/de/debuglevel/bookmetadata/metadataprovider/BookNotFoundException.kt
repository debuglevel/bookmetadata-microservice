package de.debuglevel.bookmetadata.metadataprovider

class BookNotFoundException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}