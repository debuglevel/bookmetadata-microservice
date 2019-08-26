package de.debuglevel.bookmetadata.metadataprovider

data class ISBN(val isbn: String) {
    override fun toString(): String = isbn
    val normalized = normalize(isbn)

    /**
     * Removes all non-digit characters in a ISBN
     * @param isbn ISBN formatted in any way (e.g. with dashes)
     * @return ISBN with only numbers
     */
    private fun normalize(isbn: String): String {
        return isbn.replace("[^\\d]".toRegex(), "")
    }
}