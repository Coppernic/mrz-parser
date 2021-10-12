package fr.coppernic.lib.mrz.parser.extensions

import java.text.SimpleDateFormat
import java.util.Locale

object DateExtensions {
    val mrzDateFormat = SimpleDateFormat("yyMMdd", Locale.US)
}
