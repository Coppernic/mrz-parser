package fr.coppernic.lib.mrz.parser

class CommonParser {

    fun parseNames(s: String): List<String> {
        return s.split("<<").map {
            sanitize(it)
        }
    }

    fun sanitize(s: String): String {
        return s.replace("<", " ").trim()
    }

    fun extractString(s: String, r: IntRange): String {
        return sanitize(s.substring(r))
    }
}
