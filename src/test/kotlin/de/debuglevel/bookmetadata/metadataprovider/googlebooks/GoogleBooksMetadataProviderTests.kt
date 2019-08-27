package de.debuglevel.bookmetadata.metadataprovider.googlebooks

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
class GoogleBooksMetadataProviderTests {

    @Inject
    lateinit var metadataProvider: GoogleBooksMetadataProvider

    @Inject
    var dataService: GoogleBooksDataService? = null

    @ParameterizedTest
    @MethodSource("bookProvider")
    fun `parse data from provider`(book: BookResponseDTO) {
        // Arrange

        // Act
        val isbn = ISBN(book.isbn!!)
        val retrievedBook = metadataProvider.getBook(isbn)

        // Assert
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

    @MockBean(GoogleBooksDataService::class)
    fun dataService(): GoogleBooksDataService {
        return mock(GoogleBooksDataService::class.java)
    }

    fun bookProvider() = TestDataProvider.bookProvider()
    fun dataProvider() = TestDataProvider.dataProvider()
}