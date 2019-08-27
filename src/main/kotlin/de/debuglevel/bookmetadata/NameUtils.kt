package de.debuglevel.bookmetadata

object NameUtils {
    /**
     * Converts e.g. "Aaron T. Beck" to "Beck, Aaron T."
     * This function will produce errors on any multipart lastname (e.g. "Friedemann Schulz von Thun" becomes "Thun, Friedemann Schulz von" instead of "Schulz von Thun, Friedemann")
     */
    fun convertToLastnameFirst(name: String): String {
        val parts = name.split(" ")
        val lastname = parts.last()
        val firstname = parts.take(parts.count() - 1).joinToString(" ")

        return "$lastname, $firstname"
    }
}