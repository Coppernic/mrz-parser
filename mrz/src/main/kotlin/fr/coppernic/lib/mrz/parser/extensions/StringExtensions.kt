package fr.coppernic.lib.mrz.parser.extensions

import fr.coppernic.lib.log.MrzParserDefines
import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.parser.extensions.MrzRegexes.fillCharacterRegex
import fr.coppernic.lib.mrz.parser.extensions.MrzRegexes.nameDelimiter
import fr.coppernic.lib.mrz.parser.extensions.MrzRegexes.newLineRegex
import java.text.SimpleDateFormat
import java.util.Date

private object MrzRegexes {
    val fillCharacterRegex = "(<)+".toRegex()
    val newLineRegex = "(\n\r)|(\r)".toRegex()
    const val nameDelimiter = "<<"
}

internal fun String.separate() = replace(newLineRegex, "\n")
    .trim()
    .split("\n")

internal fun String.sanitize(): String = replace(fillCharacterRegex, " ").trim()

internal fun String.extractNumber(r: IntRange): Int = substring(r).sanitize().toIntOrNull() ?: 0

internal fun String.extractNames(r: IntRange = 0 until length): Pair<String, String> =
    substring(r).split(nameDelimiter).run {
        (getOrElse(0) { "" }.sanitize()) to (getOrElse(1) { "" }.sanitize())
    }

internal fun String.computeCheckDigit(lenient: Boolean = false): Int {
    val weight = arrayOf(7, 3, 1)
    return toCharArray().foldIndexed(0) { index, acc, c ->
        acc + c.toNumber(lenient) * weight[index % 3]
    } % 10
}

internal fun String.extractDate(format: SimpleDateFormat): Date? {
    return try {
        format.parse(this)
    } catch (e: Exception) {
        if (MrzParserDefines.verbose) {
            MrzParserDefines.LOG.debug("$e, during parsing of $this with format ${format.toPattern()}")
        }
        null
    }
}

internal fun String.divide(n: Int): MutableList<String> {
    val len = length / n
    return mutableListOf<String>().apply {
        for (i: Int in 0 until n) {
            add(substring((i * len) until (i * len + len)))
        }
    }
}

internal fun Char.toNumber(lenient: Boolean = false): Int {
    return when (this) {
        in 'A'..'Z' -> this - 'A' + 10
        in '0'..'9' -> this - '0'
        '<' -> 0
        else -> {
            if (lenient) {
                0
            } else {
                throw MrzParserException(ErrorType.WrongFormat())
            }
        }
    }
}
