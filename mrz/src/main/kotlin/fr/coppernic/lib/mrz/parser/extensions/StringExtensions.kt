package fr.coppernic.lib.mrz.parser.extensions

import fr.coppernic.lib.log.LogDefines
import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzParserException
import java.text.SimpleDateFormat
import java.util.Date

internal fun String.sanitize(): String = replace("<", " ").trim()

internal fun String.extract(r: IntRange): String = substring(r).sanitize()

internal fun String.extractNames(r: IntRange = 0 until length): Pair<String, String> =
    substring(r).split("<<").run {
        (getOrElse(0) { "" }.sanitize()) to (getOrElse(1) { "" }.sanitize())
    }

internal fun String.computeCheckDigit(): Int {
    val weight = arrayOf(7, 3, 1)
    return toCharArray().foldIndexed(0) { index, acc, c ->
        acc + c.toNumber() * weight[index % 3]
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

internal fun Char.toNumber(): Int {
    return when (this) {
        in 'A'..'Z' -> this - 'A' + 10
        in '0'..'9' -> this - '0'
        '<' -> 0
        else -> throw MrzParserException(ErrorType.WrongFormat())
    }
}
