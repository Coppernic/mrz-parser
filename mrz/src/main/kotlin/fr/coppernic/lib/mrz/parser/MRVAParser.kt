package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzBuilder
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.extensions.extract
import fr.coppernic.lib.mrz.parser.extensions.extractNames

/**
 * Passport parser
 */
class MRVAParser : DocumentParser {
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
            documentType = MrzDocumentType.fromMrz(first.extract(docRange))
            countryCode = first.extract(countryRange)
            documentNumber = second.substring(documentNumberRange)
            documentNumberHash = second.substring(documentNumberHashRange).toInt()
            optionalData = second.extract(opt1Range)
            birthdate = second.substring(birthDateRange)
            birthdateHash = second.substring(birthdayHashRange).toInt()
            sex = MrzSex.fromMrz(second[sexRange])
            expiryDate = second.substring(expiryDateRange)
            expiryDateHash = second.substring(expiryDateHashRange).toInt()
            nationalityCountryCode = second.extract(natRange)
            first.extractNames(namesRange).let {
                surnames = it.first
                givenNames = it.second
            }
        }.build()
    }
}
