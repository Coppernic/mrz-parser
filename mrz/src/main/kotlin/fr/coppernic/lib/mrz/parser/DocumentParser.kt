package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.parser.generic.MRVAParser
import fr.coppernic.lib.mrz.parser.generic.MRVBParser
import fr.coppernic.lib.mrz.parser.generic.Td1Parser
import fr.coppernic.lib.mrz.parser.generic.Td2Parser
import fr.coppernic.lib.mrz.parser.generic.Td3Parser

interface DocumentParser {
    fun parse(lines: List<String>, opt: MrzParserOptions): Mrz

    object ParserFactory {
        fun make(format: MrzFormat?): DocumentParser {
            return when (format) {
                MrzFormat.TD1 -> Td1Parser()
                MrzFormat.TD2 -> Td2Parser()
                MrzFormat.TD3 -> Td3Parser()
                MrzFormat.MRVB -> MRVBParser()
                MrzFormat.MRVA -> MRVAParser()
                else -> throw MrzParserException(ErrorType.WrongFormat())
            }
        }
    }
}
