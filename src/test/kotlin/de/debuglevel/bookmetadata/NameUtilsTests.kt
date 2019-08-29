package de.debuglevel.bookmetadata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NameUtilsTests {
    @ParameterizedTest
    @MethodSource("nameProvider")
    fun `parse data from provider`(testData: NameTestData) {
        // Arrange

        // Act
        val result = NameUtils.convertToLastnameFirst(testData.value)

        // Assert
        assertThat(result).isEqualTo(testData.expected)
    }

    fun nameProvider() = Stream.of(
        NameTestData(value = null, expected = null),
        NameTestData(value = "Heinz", expected = "Heinz"),
        NameTestData(value = "Aaron T. Beck", expected = "Beck, Aaron T."),
        NameTestData(value = "Alan Turing", expected = "Turing, Alan"),
        NameTestData(value = "H. P. Lovecraft", expected = "Lovecraft, H. P."),
        NameTestData(value = "Horst von und zu Jenem", expected = "Jenem, Horst von und zu"),
        NameTestData(value = "Friedemann Schulz von Thun", expected = "Thun, Friedemann Schulz von"),
        NameTestData(value = "Alan Turing, Ada Lovelace", expected = "Turing, Alan; Lovelace, Ada"),
        NameTestData(value = "Alan Turing,Ada Lovelace", expected = "Turing, Alan; Lovelace, Ada"),
        NameTestData(value = "Alan Turing,  Ada Lovelace", expected = "Turing, Alan; Lovelace, Ada"),
        NameTestData(value = "Alan Turing; Ada Lovelace", expected = "Turing, Alan; Lovelace, Ada"),
        NameTestData(
            value = "Friedemann Schulz von Thun ; Dagmar Kumbier (Hg.)",
            expected = "Thun, Friedemann Schulz von; Kumbier, Dagmar (Hg.)"
        )
    )

    data class NameTestData(
        val value: String?,
        val expected: String? = null
    )
}