package fr.coppernic.lib.mrz.parser.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateExtensions {
    val mrzDateFormat = SimpleDateFormat("yyMMdd", Locale.US).apply {
        // Default rule for 2 digits date is -80 +20 from now.
        // For VISA and documents, it can never be in the range now, +20
        set2DigitYearStart(
            Calendar.getInstance().apply {
                add(Calendar.YEAR, -100)
            }.time
        )
    }
    val mrzDateExpiryFormat = SimpleDateFormat("yyMMdd", Locale.US)
}
