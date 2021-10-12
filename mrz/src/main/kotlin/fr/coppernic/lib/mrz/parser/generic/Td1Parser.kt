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

internal class Td1Parser : DocumentParser {
    companion object {
        private val docRange = 0..1
        private val countryRange = 2..4
        private val documentNumberRange = 5..13
        private val documentNumberHashRange = 14..14
        private val opt1Range = 15..29
        private val birthDateRange = 0..5
        private val birthdayHashRange = 6..6
        private const val sexRange = 7
        private val expiryDateRange = 8..13
        private val expiryDateHashRange = 14..14
        private val natRange = 15..17
        private val opt2Range = 18..28
        private val finalHashRange = 29..29
        private val namesRange = 0..29
        private val listOfRangesForFinalHash = listOf(
            0 to 5..29, 1 to 0..6, 1 to 8..14, 1 to 18..28
        )
    }

    override fun parse(lines: List<String>, opt: MrzParserOptions): Mrz {
        val first = lines[0]
        val second = lines[1]
        val third = lines[2]
        return MrzBuilder(opt).apply {
            format = MrzFormat.TD1
            documentType = MrzDocumentType.fromMrz(first.substring(docRange))
            countryCode = first.substring(countryRange)
            documentNumber = first.substring(documentNumberRange)
            documentNumberHash = first.extractNumber(documentNumberHashRange)
            optionalData = first.substring(opt1Range)
            birthdate = second.substring(birthDateRange)
            birthdateHash = second.extractNumber(birthdayHashRange)
            sex = MrzSex.fromMrz(second[sexRange])
            expiryDate = second.substring(expiryDateRange)
            expiryDateHash = second.extractNumber(expiryDateHashRange)
            nationalityCountryCode = second.substring(natRange)
            optionalData2 = second.substring(opt2Range)
            finalHashString = listOfRangesForFinalHash.joinToString("") {
                lines[it.first].substring(it.second)
            }
            finalHash = second.extractNumber(finalHashRange)
            third.extractNames().let {
                surnames = it.first
                givenNames = it.second
            }
        }.build()
    }
}
