package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.mrz.Mrz

interface DocumentParser {
    fun parse(lines: List<String>, opt: MrzParserOptions): Mrz
}
