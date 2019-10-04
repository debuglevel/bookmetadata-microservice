package de.debuglevel.bookmetadata.metadataprovider.dnb

import de.debuglevel.bookmetadata.BookResponseDTO
import de.debuglevel.bookmetadata.metadataprovider.ISBN
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DnbMetadataProviderTests {

    @Inject
    lateinit var metadataProvider: DnbMetadataProvider

    @Inject
    var dataService: DnbDataService? = null

    @ParameterizedTest
    @MethodSource("bookProvider")
    fun `parse data from provider`(book: BookResponseDTO) {
        // Arrange

        // Act
        val isbn = ISBN(book.isbn!!)
        val retrievedBook = metadataProvider.getBook(isbn)

        // Assert
        // assert each field separately, because it is very cumbersome to find the toString() differences if any does not match
        assertThat(retrievedBook.isbn).isEqualTo(book.isbn)
        assertThat(retrievedBook.requestedIsbn).isEqualTo(book.requestedIsbn)
        assertThat(retrievedBook.title).isEqualTo(book.title)
        assertThat(retrievedBook.subtitle).isEqualTo(book.subtitle)
        assertThat(retrievedBook.combinedTitle).isEqualTo(book.combinedTitle)
        assertThat(retrievedBook.author).isEqualTo(book.author)
        assertThat(retrievedBook.year).isEqualTo(book.year)
        assertThat(retrievedBook.edition).isEqualTo(book.edition)
        assertThat(retrievedBook.publisher).isEqualTo(book.publisher)
        assertThat(retrievedBook.place).isEqualTo(book.place)
        assertThat(retrievedBook.description).isEqualTo(book.description)
        assertThat(retrievedBook.source).isEqualTo(book.source)
        assertThat(retrievedBook.series).isEqualTo(book.series)
        assertThat(retrievedBook.volume).isEqualTo(book.volume)
        assertThat(retrievedBook.price).isEqualTo(book.price)
        assertThat(retrievedBook.pages).isEqualTo(book.pages)
        assertThat(retrievedBook.tableOfContentsUrl).isEqualTo(book.tableOfContentsUrl)
        assertThat(retrievedBook.abstractUrl).isEqualTo(book.abstractUrl)
        assertThat(retrievedBook.abstract).isEqualTo(book.abstract)
        assertThat(retrievedBook.language).isEqualTo(book.language)
        assertThat(retrievedBook.error).isEqualTo(book.error)

        assertThat(retrievedBook).isEqualTo(book)
    }

    // TODO: @BeforeAll does not work. Why?
    @BeforeEach
    fun `set up dataService mock`() {
        for (bookData in dataProvider()) {
            `when`(dataService!!.fetchData(ISBN(bookData.isbn)))
                .then { invocation -> bookData.data }

            // check that mock works
            val resultData = dataService!!.fetchData(ISBN(bookData.isbn))
            Assertions.assertEquals(bookData.data, resultData)
            //verify(dataService)?.fetchData(ISBN(bookData.isbn))
        }
    }

    @MockBean(DnbDataService::class)
    fun dataService(): DnbDataService {
        return mock(DnbDataService::class.java)
    }

    fun bookProvider() = TestDataProvider.bookProvider()
    fun dataProvider() = TestDataProvider.dataProvider()
}