package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.parser.DocumentParser

class MrzParser {
    fun parseOrNull(s: String): Mrz? {
        return try {
            parse(s)
        } catch (e: Exception) {
            null
        }
    }

    fun parse(s: String): Mrz {
        return parseLines(s.split("\n"))
    }

    fun parseLinesOrNull(lines: List<String>): Mrz? {
        return try {
            parseLines(lines)
        } catch (e: Exception) {
            null
        }
    }

    fun parseLines(lines: List<String>): Mrz {
        val format = getFormat(lines)
        val parser = DocumentParser.ParserFactory.make(format)
        return parser.parse(lines)
    }

    /**
     * Return format of MRZ
     */
    internal fun getFormat(lines: List<String>): MrzFormat {
        return MrzFormat.values().firstOrNull {
            it.lineCount == lines.size &&
                it.lineLen == lines.getLen()
        } ?: throw MrzParserException(ErrorType.WrongFormat)
    }

    /**
     * Get the length of a line and throw an error if lines do not have the same length
     */
    internal fun List<String>.getLen(): Int {
        val len = getOrNull(0)?.length ?: throw MrzParserException(ErrorType.WrongFormat)
        return if (all { it.length == len }) {
            len
        } else {
            throw MrzParserException(ErrorType.WrongFormat)
        }
    }
}
