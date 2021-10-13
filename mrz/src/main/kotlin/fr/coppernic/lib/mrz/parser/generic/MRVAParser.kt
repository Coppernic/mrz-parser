package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.DocumentParser
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.parser.builder.MrzBuilder
import fr.coppernic.lib.mrz.parser.extensions.extractNames
import fr.coppernic.lib.mrz.parser.extensions.extractNumber

/**
 * Passport parser
 */
internal class MRVAParser : DocumentParser {
    companion object {
        private val docRange = 0..1
        private val countryRange = 2..4
        private val documentNumberRange = 0..8
        private val documentNumberHashRange = 9..9
        private val opt1Range = 28..43
        private val birthDateRange = 13..18
        private val birthdayHashRange = 19..19
        private const val sexRange = 20
        private val expiryDateRange = 21..26
        private val expiryDateHashRange = 27..27
        private val natRange = 10..12
        private val namesRange = 5..43
    }

    override fun parse(lines: List<String>, opt: MrzParserOptions): Mrz {
        val first = lines[0]
        val second = lines[1]
        return MrzBuilder(opt).apply {
            format = MrzFormat.MRVA
            documentType = MrzDocumentType.fromMrz(first.substring(docRange))
            countryCode = first.substring(countryRange)
            documentNumber = second.substring(documentNumberRange)
            documentNumberHash = second.extractNumber(documentNumberHashRange)
            optionalData = second.substring(opt1Range)
            birthdate = second.substring(birthDateRange)
            birthdateHash = second.extractNumber(birthdayHashRange)
            sex = MrzSex.fromMrz(second[sexRange])
            expiryDate = second.substring(expiryDateRange)
            expiryDateHash = second.extractNumber(expiryDateHashRange)
            nationalityCountryCode = second.substring(natRange)
            first.extractNames(namesRange).let {
                surnames = it.first
                givenNames = it.second
            }
            fullMrz = lines.joinToString("\n")
        }.build()
    }
}
