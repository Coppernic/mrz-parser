package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.parser.DocumentParser
import fr.coppernic.lib.mrz.parser.MrzParserOptions

class MrzParser {
    fun parseOrNull(s: String, opt: MrzParserOptions = MrzParserOptions()): Mrz? {
        return try {
            parse(s, opt)
        } catch (e: Exception) {
            null
        }
    }

    fun parse(s: String, opt: MrzParserOptions = MrzParserOptions()): Mrz {
        return parseLines(s.split("\n"), opt)
    }

    fun parseLinesOrNull(lines: List<String>, opt: MrzParserOptions = MrzParserOptions()): Mrz? {
        return try {
            parseLines(lines, opt)
        } catch (e: Exception) {
            null
        }
    }

    fun parseLines(lines: List<String>, opt: MrzParserOptions = MrzParserOptions()): Mrz {
        val format = getFormat(lines)
        val parser = DocumentParser.ParserFactory.make(format)
        return parser.parse(lines, opt)
    }

    /**
     * Return format of MRZ
     */
    internal fun getFormat(lines: List<String>): MrzFormat {
        return MrzFormat.values().firstOrNull {
            it.lineCount == lines.size &&
                it.lineLen == lines.getAndVerifyLen()
        } ?: throw MrzParserException(ErrorType.WrongFormat())
    }

    /**
     * Get the length of a line and throw an error if lines do not have the same length
     */
    internal fun List<String>.getAndVerifyLen(): Int {
        val len = getOrNull(0)?.length ?: throw MrzParserException(ErrorType.WrongFormat())
        return if (all { it.length == len }) {
            len
        } else {
            throw MrzParserException(ErrorType.WrongFormat())
        }
    }
}
