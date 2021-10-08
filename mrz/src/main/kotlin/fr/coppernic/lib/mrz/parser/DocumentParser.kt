package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import java.text.SimpleDateFormat
import java.util.Locale

abstract class DocumentParser {
    abstract fun parse(lines: List<String>): Mrz

    internal fun parseNames(s: String): List<String> {
        return s.split("<<").map {
            sanitize(it)
        }
    }

    internal fun sanitize(s: String): String {
        return s.replace("<", " ").trim()
    }

    companion object {
        val mrzDateFormat = SimpleDateFormat("yyMMdd", Locale.US)
    }

    object ParserFactory {
        fun make(format: MrzFormat?): DocumentParser {
            return when (format) {
                MrzFormat.TD1 -> Td1Parser()
                MrzFormat.TD2 -> TODO() // Td2Parser()
                MrzFormat.TD3 -> TODO() // Td3Parser()
                else -> throw MrzParserException(ErrorType.WrongFormat)
            }
        }
    }
}
