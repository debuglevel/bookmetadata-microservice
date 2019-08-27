package de.debuglevel.bookmetadata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NameUtilsTests {
    @ParameterizedTest
    @CsvSource(
        "'Aaron T. Beck', 'Beck, Aaron T.'",
        "'Alan Turing', 'Turing, Alan'",
        "'H. P. Lovecraft', 'Lovecraft, H. P.'",
        "'Horst von und zu Jenem', 'Jenem, Horst von und zu'",
        "'Friedemann Schulz von Thun', 'Thun, Friedemann Schulz von'",
        "'Alan Turing, Ada Lovelace', 'Turing, Alan; Lovelace, Ada'",
        "'Alan Turing,Ada Lovelace', 'Turing, Alan; Lovelace, Ada'",
        "'Alan Turing,  Ada Lovelace', 'Turing, Alan; Lovelace, Ada'"
    )
    fun `parse data from provider`(value: String, expected: String) {
        // Arrange

        // Act
        val result = NameUtils.convertToLastnameFirst(value)

        // Assert
        assertThat(result).isEqualTo(expected)
    }
}