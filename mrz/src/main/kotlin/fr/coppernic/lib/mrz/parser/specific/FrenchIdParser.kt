package fr.coppernic.lib.mrz.parser.specific

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.DocumentParser
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.parser.builder.MrzBuilder
import fr.coppernic.lib.mrz.parser.extensions.sanitize

/**
 * Passport parser
 */
internal class FrenchIdParser : DocumentParser {
    companion object {
        private val docRange = 0..1
        private val countryRange = 2..4
        private val documentNumberRange = 0..11
        private val documentNumberHashRange = 12..12
        private val opt1Range = 30..35
        private val birthDateRange = 27..32
        private val birthdayHashRange = 33..33
        private const val sexRange = 34
        private val finalHashRange = 35..35
        private val listOfRangesForFinalHash = listOf(
            0 to 0..35,
            1 to 0..34,
        )
        private val surnameRange = 5..29
        private val givenNameRange = 13..26
    }

    override fun parse(lines: List<String>, opt: MrzParserOptions): Mrz {
        val first = lines[0]
        val second = lines[1]
        return MrzBuilder(opt).apply {
            format = MrzFormat.FRENCH_ID
            documentType = MrzDocumentType.fromMrz(first.substring(docRange))
            countryCode = first.substring(countryRange)
            documentNumber = second.substring(documentNumberRange)
            documentNumberHash = second.substring(documentNumberHashRange).toInt()
            optionalData = first.substring(opt1Range)
            birthdate = second.substring(birthDateRange)
            birthdateHash = second.substring(birthdayHashRange).toInt()
            sex = MrzSex.fromMrz(second[sexRange])

            finalHashString = listOfRangesForFinalHash.joinToString("") {
                lines[it.first].substring(it.second)
            }

            finalHash = second.substring(finalHashRange).toInt()
            surnames = first.substring(surnameRange).sanitize()
            givenNames = second.substring(givenNameRange).sanitize()
            fullMrz = lines.joinToString("\n")
        }.build()
    }
}
