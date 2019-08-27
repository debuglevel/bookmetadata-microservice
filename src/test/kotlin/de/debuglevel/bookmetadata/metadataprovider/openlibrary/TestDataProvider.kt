package de.debuglevel.bookmetadata.metadataprovider.openlibrary

import de.debuglevel.bookmetadata.BookResponseDTO
import java.util.stream.Stream

object TestDataProvider {
    fun bookProvider() = Stream.of(
        BookResponseDTO("9783551551672").apply {
            requestedIsbn = "9783551551672"
            isbn = requestedIsbn
            title = "Harry Potter und der Stein der Weisen"
            combinedTitle = title
            author = "Rowling, J. K."
            //author = "Joanne K. Rowling. Aus dem Engl. von Klaus Fritz"
            publisher = "Carlsen"
            year = "1998"
            pages = "335"
            place = "Hamburg, Germany"
        }
        //,
//        BookResponseDTO("9783868943238").apply {
//        },
//        BookResponseDTO("9783621285674").apply {
//        },
//        BookResponseDTO("9783801726973").apply {
//        }
    )

    fun dataProvider() = Stream.of(
        BookData(
            "9783551551672",
            """
{"ISBN:9783551551672": {"publishers": [{"name": "Carlsen"}], "pagination": "335p. ;", "identifiers": {"openlibrary": ["OL20176009M"], "isbn_13": ["9783551551672"], "isbn_10": ["3-551-55167-7"], "wikidata": ["Q58465058"], "oclc": ["441683420"], "goodreads": ["142293"]}, "links": [{"url": "https://www.jkrowling.com/book/harry-potter-philosophers-stone/", "title": "Harry Potter and the Philosopher's Stone - J.K. Rowling"}, {"url": "https://en.wikipedia.org/wiki/Harry_Potter_and_the_Philosopher%27s_Stone", "title": "Harry Potter and the Philosopher's Stone - Wikipedia"}, {"url": "https://www.theguardian.com/childrens-books-site/2015/jul/29/harry-potter-and-the-philosophers-stone-j-k-rowling-review", "title": "Harry Potter and the Philosopher's Stone by J.K. Rowling \u2013 review (theguardian.com)"}, {"url": "https://www.theguardian.com/childrens-books-site/2015/mar/01/review-j-k-rowling-harry-potter-philosophers-stone", "title": "Harry Potter and the Philosopher's Stone by J.K. Rowling - review (theguardian.com)"}], "title": "Harry Potter und der Stein der Weisen", "url": "https://openlibrary.org/books/OL20176009M/Harry_Potter_und_der_Stein_der_Weisen", "number_of_pages": 335, "subject_places": [{"url": "https://openlibrary.org/subjects/place:england", "name": "England"}, {"url": "https://openlibrary.org/subjects/place:hogwarts_school_of_witchcraft_and_wizardry", "name": "Hogwarts School of Witchcraft and Wizardry"}, {"url": "https://openlibrary.org/subjects/place:4_privet_drive", "name": "4 Privet Drive"}, {"url": "https://openlibrary.org/subjects/place:diagon_alley", "name": "Diagon Alley"}, {"url": "https://openlibrary.org/subjects/place:leaky_cauldron", "name": "Leaky Cauldron"}, {"url": "https://openlibrary.org/subjects/place:gringotts_wizarding_bank", "name": "Gringotts Wizarding Bank"}, {"url": "https://openlibrary.org/subjects/place:forbidden_forest", "name": "Forbidden Forest"}, {"url": "https://openlibrary.org/subjects/place:king's_cross_station", "name": "King's Cross Station"}, {"url": "https://openlibrary.org/subjects/place:platform_nine_and_three-quarters", "name": "Platform Nine and Three-quarters"}], "subjects": [{"url": "https://openlibrary.org/subjects/ghosts", "name": "Ghosts"}, {"url": "https://openlibrary.org/subjects/monsters", "name": "Monsters"}, {"url": "https://openlibrary.org/subjects/vampires", "name": "Vampires"}, {"url": "https://openlibrary.org/subjects/witches", "name": "Witches"}, {"url": "https://openlibrary.org/subjects/challenges_and_overcoming_obstacles", "name": "Challenges and Overcoming Obstacles"}, {"url": "https://openlibrary.org/subjects/magic_and_supernatural", "name": "Magic and Supernatural"}, {"url": "https://openlibrary.org/subjects/cleverness", "name": "Cleverness"}, {"url": "https://openlibrary.org/subjects/school_life", "name": "School Life"}, {"url": "https://openlibrary.org/subjects/school_stories", "name": "school stories"}, {"url": "https://openlibrary.org/subjects/wizards", "name": "Wizards"}, {"url": "https://openlibrary.org/subjects/magic", "name": "Magic"}, {"url": "https://openlibrary.org/subjects/magia", "name": "MAGIA"}, {"url": "https://openlibrary.org/subjects/magos", "name": "MAGOS"}, {"url": "https://openlibrary.org/subjects/juvenile_fiction", "name": "Juvenile fiction"}, {"url": "https://openlibrary.org/subjects/fiction", "name": "Fiction"}, {"url": "https://openlibrary.org/subjects/novelas_inglesas", "name": "NOVELAS INGLESAS"}, {"url": "https://openlibrary.org/subjects/schools", "name": "Schools"}, {"url": "https://openlibrary.org/subjects/orphans", "name": "orphans"}, {"url": "https://openlibrary.org/subjects/fantasy_fiction", "name": "fantasy fiction"}, {"url": "https://openlibrary.org/subjects/england_in_fiction", "name": "England in fiction"}, {"url": "https://openlibrary.org/subjects/witches_in_fiction", "name": "Witches in fiction"}, {"url": "https://openlibrary.org/subjects/wizards_in_fiction", "name": "Wizards in fiction"}, {"url": "https://openlibrary.org/subjects/alchemy", "name": "Alchemy"}, {"url": "https://openlibrary.org/subjects/new_york_times_bestseller", "name": "New York Times bestseller"}, {"url": "https://openlibrary.org/subjects/juvenile_literature", "name": "Juvenile literature"}, {"url": "https://openlibrary.org/subjects/magic_in_fiction", "name": "Magic in fiction"}, {"url": "https://openlibrary.org/subjects/open_library_staff_picks", "name": "Open Library Staff Picks"}, {"url": "https://openlibrary.org/subjects/juvenile_audience", "name": "Juvenile audience"}, {"url": "https://openlibrary.org/subjects/children's_stories", "name": "Children's stories"}, {"url": "https://openlibrary.org/subjects/juvenile_works", "name": "Juvenile works"}, {"url": "https://openlibrary.org/subjects/schools_in_fiction", "name": "Schools in fiction"}, {"url": "https://openlibrary.org/subjects/fantasy", "name": "Fantasy"}, {"url": "https://openlibrary.org/subjects/hogwarts_school_of_witchcraft_and_wizardry_(imaginary_place)", "name": "Hogwarts School of Witchcraft and Wizardry (Imaginary place)"}, {"url": "https://openlibrary.org/subjects/harry_potter_(fictitious_character)", "name": "Harry Potter (Fictitious character)"}, {"url": "https://openlibrary.org/subjects/ficci\u00f3n_juvenil", "name": "Ficci\u00f3n juvenil"}, {"url": "https://openlibrary.org/subjects/escuelas", "name": "Escuelas"}, {"url": "https://openlibrary.org/subjects/hogwarts_school_of_witchcraft_and_wizardry_(imaginary_organization)", "name": "Hogwarts School of Witchcraft and Wizardry (Imaginary organization)"}, {"url": "https://openlibrary.org/subjects/translations_from_english", "name": "Translations from English"}, {"url": "https://openlibrary.org/subjects/chinese_fiction", "name": "Chinese fiction"}, {"url": "https://openlibrary.org/subjects/hermione_granger_(fictitious_character)", "name": "Hermione Granger (Fictitious character)"}], "subject_people": [{"url": "https://openlibrary.org/subjects/person:harry_potter", "name": "Harry Potter"}, {"url": "https://openlibrary.org/subjects/person:ron_weasley", "name": "Ron Weasley"}, {"url": "https://openlibrary.org/subjects/person:rony", "name": "Rony"}, {"url": "https://openlibrary.org/subjects/person:hermione_granger", "name": "Hermione Granger"}, {"url": "https://openlibrary.org/subjects/person:neville_longbottom", "name": "Neville Longbottom"}, {"url": "https://openlibrary.org/subjects/person:rubens_hagrid", "name": "Rubens Hagrid"}, {"url": "https://openlibrary.org/subjects/person:albus_dumbledore", "name": "Albus Dumbledore"}, {"url": "https://openlibrary.org/subjects/person:minerva_mcgonagall", "name": "Minerva McGonagall"}, {"url": "https://openlibrary.org/subjects/person:nicolas_flamel", "name": "Nicolas Flamel"}, {"url": "https://openlibrary.org/subjects/person:norbert", "name": "Norbert"}, {"url": "https://openlibrary.org/subjects/person:sorting_hat", "name": "Sorting Hat"}, {"url": "https://openlibrary.org/subjects/person:severus_snape", "name": "Severus Snape"}, {"url": "https://openlibrary.org/subjects/person:quirinus_quirrell", "name": "Quirinus Quirrell"}, {"url": "https://openlibrary.org/subjects/person:petunia_dursley", "name": "Petunia Dursley"}, {"url": "https://openlibrary.org/subjects/person:draco_malfoy", "name": "Draco Malfoy"}, {"url": "https://openlibrary.org/subjects/person:argus_filch", "name": "Argus Filch"}, {"url": "https://openlibrary.org/subjects/person:vernon_dursley", "name": "Vernon Dursley"}, {"url": "https://openlibrary.org/subjects/person:valter", "name": "Valter"}, {"url": "https://openlibrary.org/subjects/person:dudley_dursley", "name": "Dudley Dursley"}, {"url": "https://openlibrary.org/subjects/person:duda", "name": "Duda"}, {"url": "https://openlibrary.org/subjects/person:fred_weasley", "name": "Fred Weasley"}, {"url": "https://openlibrary.org/subjects/person:george_weasley", "name": "George Weasley"}, {"url": "https://openlibrary.org/subjects/person:jorge", "name": "Jorge"}, {"url": "https://openlibrary.org/subjects/person:ginny_weasley", "name": "Ginny Weasley"}, {"url": "https://openlibrary.org/subjects/person:gina", "name": "Gina"}, {"url": "https://openlibrary.org/subjects/person:molly_weasley", "name": "Molly Weasley"}, {"url": "https://openlibrary.org/subjects/person:percy_weasley", "name": "Percy Weasley"}, {"url": "https://openlibrary.org/subjects/person:vincent_crabbe", "name": "Vincent Crabbe"}, {"url": "https://openlibrary.org/subjects/person:gregory_goyle", "name": "Gregory Goyle"}, {"url": "https://openlibrary.org/subjects/person:lee_jordan", "name": "Lee Jordan"}, {"url": "https://openlibrary.org/subjects/person:scabbers", "name": "Scabbers"}, {"url": "https://openlibrary.org/subjects/person:hannah_abbott", "name": "Hannah Abbott"}, {"url": "https://openlibrary.org/subjects/person:bloody_baron", "name": "Bloody Baron"}, {"url": "https://openlibrary.org/subjects/person:susan_bones", "name": "Susan Bones"}, {"url": "https://openlibrary.org/subjects/person:terry_boot", "name": "Terry Boot"}, {"url": "https://openlibrary.org/subjects/person:mandy_brocklehurst", "name": "Mandy Brocklehurst"}, {"url": "https://openlibrary.org/subjects/person:lavender_brown", "name": "Lavender Brown"}, {"url": "https://openlibrary.org/subjects/person:millicent_bulstrode", "name": "Millicent Bulstrode"}, {"url": "https://openlibrary.org/subjects/person:nearly_headless_nick", "name": "Nearly Headless Nick"}, {"url": "https://openlibrary.org/subjects/person:fat_friar", "name": "Fat Friar"}, {"url": "https://openlibrary.org/subjects/person:fat_lady", "name": "Fat Lady"}, {"url": "https://openlibrary.org/subjects/person:justin_finch-fetchley", "name": "Justin Finch-Fetchley"}, {"url": "https://openlibrary.org/subjects/person:seamus_finnigan", "name": "Seamus Finnigan"}, {"url": "https://openlibrary.org/subjects/person:morag_macdougal", "name": "Morag MacDougal"}, {"url": "https://openlibrary.org/subjects/person:lily_moon", "name": "Lily Moon"}, {"url": "https://openlibrary.org/subjects/person:pansy_parkinson", "name": "Pansy Parkinson"}, {"url": "https://openlibrary.org/subjects/person:padma_patil", "name": "Padma Patil"}, {"url": "https://openlibrary.org/subjects/person:parvati_patil", "name": "Parvati Patil"}, {"url": "https://openlibrary.org/subjects/person:chap\u00e9u_seletor", "name": "Chap\u00e9u Seletor"}, {"url": "https://openlibrary.org/subjects/person:peeves", "name": "Peeves"}, {"url": "https://openlibrary.org/subjects/person:dean_thomas", "name": "Dean Thomas"}, {"url": "https://openlibrary.org/subjects/person:lisa_turpin", "name": "Lisa Turpin"}, {"url": "https://openlibrary.org/subjects/person:blaise_zabini", "name": "Blaise Zabini"}, {"url": "https://openlibrary.org/subjects/person:lil\u00e1", "name": "Lil\u00e1"}], "key": "/books/OL20176009M", "authors": [{"url": "https://openlibrary.org/authors/OL23919A/J._K._Rowling", "name": "J. K. Rowling"}], "publish_date": "1998", "by_statement": "aus dem Englischen von Klaus Fritz.", "publish_places": [{"name": "Hamburg, Germany"}]}}
            """.trimIndent()
        ),
        BookData(
            "9783868943238",
            """
            """.trimIndent()
        ),
        BookData(
            "9783621285674",
            """
            """.trimIndent()
        ),
        BookData(
            "9783801726973",
            """
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