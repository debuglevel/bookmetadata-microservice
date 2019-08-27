package de.debuglevel.bookmetadata

object NameUtils {
    /**
     * Converts e.g. "Aaron T. Beck" to "Beck, Aaron T."
     * This function will produce errors on any multipart lastname (e.g. "Friedemann Schulz von Thun" becomes "Thun, Friedemann Schulz von" instead of "Schulz von Thun, Friedemann")
     * If the given String contains a comma, it's assumed that there are multiple names given ("Alan Turing, Ada Lovelace") which will be converted to "Turing, Alan; Lovelace, Ada"
     */
    fun convertToLastnameFirst(name: String): String {
        val regex = Regex("[,;] *")
        return name.split(regex)
            .map { convertOne(it) }
            .joinToString("; ")
    }

    private fun convertOne(name: String): String {
        val parts = name.split(" ")
        val lastname = parts.last()
        val firstname = parts.take(parts.count() - 1).joinToString(" ")

        return "$lastname, $firstname"
    }
}