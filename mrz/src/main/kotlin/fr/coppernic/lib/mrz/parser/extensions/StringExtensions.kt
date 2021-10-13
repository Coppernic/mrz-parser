package fr.coppernic.lib.mrz.parser.extensions

import fr.coppernic.lib.log.LogDefines
import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.parser.extensions.MrzRegexes.fillCharacter
import fr.coppernic.lib.mrz.parser.extensions.MrzRegexes.nameDelimiter
import java.text.SimpleDateFormat
import java.util.Date

private object MrzRegexes {
    val fillCharacter = "(<)+".toRegex()
    const val nameDelimiter = "<<"
}

internal fun String.sanitize(): String = replace(fillCharacter, " ").trim()

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
        if (LogDefines.verbose) {
            LogDefines.LOG.debug("$e, during parsing of $this with format ${format.toPattern()}")
        }
        null
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
