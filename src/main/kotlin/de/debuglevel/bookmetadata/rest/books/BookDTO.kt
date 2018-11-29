package de.debuglevel.bookmetadata.rest.books

data class BookDTO(var isbn: String?) {
    var title: String? = null
    var subtitle: String? = null
    var combinedTitle: String? = null
    var author: String? = null
    var year: String? = null
    var edition: String? = null
    var publisher: String? = null
    var place: String? = null
    var description: String? = null
    var source: String? = null
    var series: String? = null
    var volume: String? = null
    var price: String? = null
    var pages: String? = null
    var tableOfContentsUrl: String? = null
    var abstractUrl: String? = null
    var abstract: String? = null
}