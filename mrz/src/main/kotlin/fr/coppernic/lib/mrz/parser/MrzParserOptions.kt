package fr.coppernic.lib.mrz.parser

data class MrzParserOptions(
    val checkDigit: Boolean = true,
    val lenient: Boolean = false
)
