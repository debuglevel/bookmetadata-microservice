package de.debuglevel.bookmetadata

object NameUtils {
    /**
     * Converts e.g. "Aaron T. Beck" to "Beck, Aaron T."
     * This function will produce errors on any multipart lastname (e.g. "Friedemann Schulz von Thun" becomes "Thun, Friedemann Schulz von" instead of "Schulz von Thun, Friedemann")
     * If the given String contains a comma, it's assumed that there are multiple names given ("Alan Turing, Ada Lovelace") which will be converted to "Turing, Alan; Lovelace, Ada"
     */
    fun convertToLastnameFirst(name: String?): String? {
        if (name.isNullOrBlank()) {
            return name
        }

        val regex = Regex(" *[,;] *")
        return name.split(regex)
            .map { it.trim() }
            .map { convertOne(it) }
            .joinToString("; ")
    }

    private fun convertOne(name: String): String {
        val parts = name.split(" ")

        if (parts.count() == 1) {
            return name
        }

        // Search the last word which is not something like "(Hrsg.)" and take it as the lastname.
        val parenthesesRegex = Regex("""[(\[].*[)\]]""")
        val lastname = parts.last { !it.matches(parenthesesRegex) }
        // Then remove the last occurence of "lastname" in the parts and concat everything
        val rest = parts
            .reversed()
            .minus(lastname)
            .reversed()
            .joinToString(" ")

        return "$lastname, $rest"
    }
}