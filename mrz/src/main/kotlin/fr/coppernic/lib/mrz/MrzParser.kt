package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.parser.ParserFactory
import fr.coppernic.lib.mrz.parser.extensions.divide

class MrzParser {
    fun parseOrNull(s: String, opt: MrzParserOptions = MrzParserOptions()): Mrz? {
        return try {
            parse(s, opt)
        } catch (e: Exception) {
            null
        }
    }

    fun parse(s: String, opt: MrzParserOptions = MrzParserOptions()): Mrz {
        return parseLines(s.trim().split("\n"), opt)
    }

    fun parseLinesOrNull(lines: List<String>, opt: MrzParserOptions = MrzParserOptions()): Mrz? {
        return try {
            parseLines(lines, opt)
        } catch (e: Exception) {
            null
        }
    }

    fun parseLines(lines: List<String>, opt: MrzParserOptions = MrzParserOptions()): Mrz {
        if (lines.isEmpty()) {
            throw MrzParserException(ErrorType.WrongFormat("Not enough lines (Actual ${lines.size})"))
        }
        // Handle case where one single line is given. Make it more understandable for parser
        val newLines = if (lines.size == 1) {
            handleSingleLine(lines[0])
        } else {
            lines
        }
        val format = getFormat(newLines)
        val parser = ParserFactory.make(format)
        return parser.parse(newLines, opt)
    }

    companion object {
        private val docRange = 0..4
    }

    /**
     * Return format of MRZ
     */
    internal fun getFormat(lines: List<String>): MrzFormat {
        val first = lines.getOrElse(0) { "" }
        if (first.length < 5) {
            throw MrzParserException(ErrorType.WrongFormat("Not enough length (Actual ${first.length})"))
        }

        val docType = first.substring(docRange)
        val lineCount = lines.size
        val lineLen = lines.getAndVerifyLen()
        var format: MrzFormat? = null

        MrzFormat.values().forEach {
            if (
                it.lineCount == lineCount &&
                it.lineLen == lineLen &&
                // We are testing the document type against the first letters of the MRZ.
                docType.startsWith(it.type) &&
                // If length of format type is greater, then it means that it is more precise. So we should
                // take the most precise format.
                it.type.length >= format?.type?.length ?: 0
            ) {
                format = it
            }
        }

        return format ?: throw MrzParserException(ErrorType.WrongFormat())
    }

    internal fun handleSingleLine(line: String): MutableList<String> {
        val potential = MrzFormat.values().firstOrNull {
            (line.length == it.lineCount * it.lineLen)
        } ?: throw MrzParserException(ErrorType.WrongFormat("Not enough lines (Actual 1)"))
        return line.divide(potential.lineCount)
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
