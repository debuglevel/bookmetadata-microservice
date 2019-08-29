package de.debuglevel.bookmetadata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HtmlConverterTests {
    @ParameterizedTest
    @CsvSource(
        "'1Hallo Welt', '1Hallo Welt'",
        "'2Hallo<br>Welt', '2Hallo\nWelt'",
        "'3Hallo<BR>Welt', '3Hallo\nWelt'",
        "'4Hallo<br />Welt', '4Hallo\nWelt'",
        "'5Hallo<br> Welt', '5Hallo\n Welt'",
        "'<p>6Hallo</p><p>Welt</p>', '6Hallo\n\nWelt'"

    )
    fun `format HTML to plaintext`(value: String, expected: String) {
        // Arrange

        // Act
        val result = HtmlConverter().toPlaintext(value)

        // Assert
        assertThat(result).isEqualTo(expected)
    }
}