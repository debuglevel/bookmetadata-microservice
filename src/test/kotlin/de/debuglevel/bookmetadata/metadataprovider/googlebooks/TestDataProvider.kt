package de.debuglevel.bookmetadata.metadataprovider.googlebooks

import de.debuglevel.bookmetadata.BookResponseDTO
import java.util.stream.Stream

object TestDataProvider {
    fun bookProvider() = Stream.of(
        BookResponseDTO("9783551551672").apply {
            requestedIsbn = "9783551551672"
            isbn = requestedIsbn
            title = "Harry Potter und der Stein der Weisen"
            combinedTitle = title
            author = "J. K. Rowling"
            //author = "Joanne K. Rowling. Aus dem Engl. von Klaus Fritz"
            abstract =
                "Nach dem Tod seiner Eltern lebt Harry Potter bei gräßlichen Verwandten. Als er zu seinem 11. Geburtstag die Berufung an die Hexen- und Zaubererschule erhält, ändert sich sein Leben gründlich. Er lernt Freundschaft, aber auch Feindschaft kennen und erlebt das lustigste Jahr seines Lebens."
            publisher = "Carlsen Verlag Gmbtl"
            year = "1998"
            pages = "335"
            place = "Hamburg"
        },
        BookResponseDTO("9783868943238").apply {
        },
        BookResponseDTO("9783621285674").apply {
            requestedIsbn = "9783621285674"
            isbn = requestedIsbn
            title = "Kognitive Therapie der Depression"
            combinedTitle = title
            //edition = "5., neu ausgestattete Auflage"
            //tableOfContentsUrl="http://d-nb.info/1127602284/04"
            //abstractUrl="http://deposit.dnb.de/cgi-bin/dokserv?id=9fe9bde43c444775be1b264cc8abab4a&prov=M&dok_var=1&dok_ext=htm"
            //abstract="Angaben aus der Verlagsmeldung <br><br><h3> Kognitive Therapie der Depression  Cognitive Therapy of Depression  / von Aaron T. Beck, A. John Rush, Brian F. Shaw, Gary Emery</h3><br><p>Der weltweit anerkannte und bewährte Ansatz zur Behandlung von Depressionen: Aaron T. Beck erklärt sein Modell und geht konkret und ausführlich auf die kognitive Therapie für depressive Patienten ein.<BR><BR>Mit Erkenntnis, Einsicht und Bewusstwerdung gegen Depression: Die kognitive Therapie der Depression nach Aaron T. Beck ist weltweit anerkannt und erfolgreich. In diesem Buch wird das bewährte Modell dargestellt. Die Behandlung depressiver Patienten und die Rolle der Emotionen in der Therapie werden ausführlich und konkret beschrieben. Im Anhang: zusätzliche Arbeitsmaterialien, Fragebogen, Listen und andere Diagnoseinstrumente sowie Forschungsarbeiten aus dem deutschsprachigen Raum. Ein umfassendes Standardwerk zur Depressionstherapie.<BR>"
            abstract =
                "Mit Erkenntnis, Einsicht und Bewusstwerdung gegen Depression: Die kognitive Therapie der Depression nach Aaron T. BEck ist weltweit anerkannt und erfolgreich. IN diesem Buch wird das bewahrte Modell dargestellt."
            author = "Aaron T. Beck, A. John Rush, Brian F. Shaw, Gary Emery"
            language = "de"
            //author = "Aaaron T. Beck, A. John Rush, Brian F. Shaw, Gary Emery"
            //publisher = "Beltz"
            year = "2017"
            pages = "415"
            //place = "Weinheim"
        },
        BookResponseDTO("9783801726973").apply {
        }
    )

    fun dataProvider() = Stream.of(
        BookData(
            "9783551551672",
            """
{
 "kind": "books#volumes",
 "totalItems": 1,
 "items": [
  {
   "kind": "books#volume",
   "id": "XvApAQAAMAAJ",
   "etag": "8+nihroME/E",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/XvApAQAAMAAJ",
   "volumeInfo": {
    "title": "Harry Potter und der Stein der Weisen",
    "authors": [
     "J. K. Rowling"
    ],
    "publisher": "Carlsen Verlag Gmbtl",
    "publishedDate": "1998-01",
    "description": "Nach dem Tod seiner Eltern lebt Harry Potter bei gräßlichen Verwandten. Als er zu seinem 11. Geburtstag die Berufung an die Hexen- und Zaubererschule erhält, ändert sich sein Leben gründlich. Er lernt Freundschaft, aber auch Feindschaft kennen und erlebt das lustigste Jahr seines Lebens.",
    "industryIdentifiers": [
     {
      "type": "OTHER",
      "identifier": "UOM:39076002631195"
     }
    ],
    "readingModes": {
     "text": false,
     "image": false
    },
    "pageCount": 335,
    "printType": "BOOK",
    "categories": [
     "Juvenile Fiction"
    ],
    "averageRating": 5.0,
    "ratingsCount": 2,
    "maturityRating": "NOT_MATURE",
    "allowAnonLogging": false,
    "contentVersion": "0.1.0.0.preview.0",
    "imageLinks": {
     "smallThumbnail": "http://books.google.com/books/content?id=XvApAQAAMAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
     "thumbnail": "http://books.google.com/books/content?id=XvApAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
    },
    "language": "de",
    "previewLink": "http://books.google.de/books?id=XvApAQAAMAAJ&dq=isbn:9783551551672&hl=&cd=1&source=gbs_api",
    "infoLink": "http://books.google.de/books?id=XvApAQAAMAAJ&dq=isbn:9783551551672&hl=&source=gbs_api",
    "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_und_der_Stein_der_Weisen.html?hl=&id=XvApAQAAMAAJ"
   },
   "saleInfo": {
    "country": "DE",
    "saleability": "NOT_FOR_SALE",
    "isEbook": false
   },
   "accessInfo": {
    "country": "DE",
    "viewability": "NO_PAGES",
    "embeddable": false,
    "publicDomain": false,
    "textToSpeechPermission": "ALLOWED",
    "epub": {
     "isAvailable": false
    },
    "pdf": {
     "isAvailable": false
    },
    "webReaderLink": "http://play.google.com/books/reader?id=XvApAQAAMAAJ&hl=&printsec=frontcover&source=gbs_api",
    "accessViewStatus": "NONE",
    "quoteSharingAllowed": false
   },
   "searchInfo": {
    "textSnippet": "Nach dem Tod seiner Eltern lebt Harry Potter bei gräßlichen Verwandten."
   }
  }
 ]
}
            """.trimIndent()
        ),
        BookData(
            "9783868943238",
            """
{
 "kind": "books#volumes",
 "totalItems": 0
}
            """.trimIndent()
        ),
        BookData(
            "9783621285674",
            """
{
 "kind": "books#volumes",
 "totalItems": 1,
 "items": [
  {
   "kind": "books#volume",
   "id": "l2DyAQAACAAJ",
   "etag": "T3B/+M1gsuA",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/l2DyAQAACAAJ",
   "volumeInfo": {
    "title": "Kognitive Therapie der Depression",
    "authors": [
     "Aaron T. Beck",
     "A. John Rush",
     "Brian F. Shaw",
     "Gary Emery"
    ],
    "publishedDate": "2017-05-15",
    "industryIdentifiers": [
     {
      "type": "ISBN_10",
      "identifier": "3621285679"
     },
     {
      "type": "ISBN_13",
      "identifier": "9783621285674"
     }
    ],
    "readingModes": {
     "text": false,
     "image": false
    },
    "pageCount": 415,
    "printType": "BOOK",
    "maturityRating": "NOT_MATURE",
    "allowAnonLogging": false,
    "contentVersion": "preview-1.0.0",
    "panelizationSummary": {
     "containsEpubBubbles": false,
     "containsImageBubbles": false
    },
    "imageLinks": {
     "smallThumbnail": "http://books.google.com/books/content?id=l2DyAQAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
     "thumbnail": "http://books.google.com/books/content?id=l2DyAQAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
    },
    "language": "de",
    "previewLink": "http://books.google.de/books?id=l2DyAQAACAAJ&dq=isbn:9783621285674&hl=&cd=1&source=gbs_api",
    "infoLink": "http://books.google.de/books?id=l2DyAQAACAAJ&dq=isbn:9783621285674&hl=&source=gbs_api",
    "canonicalVolumeLink": "https://books.google.com/books/about/Kognitive_Therapie_der_Depression.html?hl=&id=l2DyAQAACAAJ"
   },
   "saleInfo": {
    "country": "DE",
    "saleability": "NOT_FOR_SALE",
    "isEbook": false
   },
   "accessInfo": {
    "country": "DE",
    "viewability": "NO_PAGES",
    "embeddable": false,
    "publicDomain": false,
    "textToSpeechPermission": "ALLOWED",
    "epub": {
     "isAvailable": false
    },
    "pdf": {
     "isAvailable": false
    },
    "webReaderLink": "http://play.google.com/books/reader?id=l2DyAQAACAAJ&hl=&printsec=frontcover&source=gbs_api",
    "accessViewStatus": "NONE",
    "quoteSharingAllowed": false
   },
   "searchInfo": {
    "textSnippet": "Mit Erkenntnis, Einsicht und Bewusstwerdung gegen Depression: Die kognitive Therapie der Depression nach Aaron T. BEck ist weltweit anerkannt und erfolgreich. IN diesem Buch wird das bewahrte Modell dargestellt."
   }
  }
 ]
}
            """.trimIndent()
        ),
        BookData(
            "9783801726973",
            """
{
 "kind": "books#volumes",
 "totalItems": 0
}
            """.trimIndent()
        )
        ,
        BookData(
            "dummy",
            """
                dummy
            """.trimIndent()
        )
    )

    data class BookData(val isbn: String, val data: String)
}