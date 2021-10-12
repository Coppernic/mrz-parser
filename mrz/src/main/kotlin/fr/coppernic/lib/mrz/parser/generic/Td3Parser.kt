package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzBuilder
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.DocumentParser
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.parser.extensions.extract
import fr.coppernic.lib.mrz.parser.extensions.extractNames
import fr.coppernic.lib.mrz.parser.extensions.extractNumber

/**
 * Passport parser
 */
internal class Td3Parser : DocumentParser {
    companion object {
        private val docRange = 0..1
        private val countryRange = 2..4
        private val documentNumberRange = 0..8
        private val documentNumberHashRange = 9..9
        private val opt1Range = 28..41
        private val birthDateRange = 13..18
        private val birthdayHashRange = 19..19
        private const val sexRange = 20
        private val expiryDateRange = 21..26
        private val expiryDateHashRange = 27..27
        private val natRange = 10..12
        private val finalHashRange = 43..43
        private val listOfRangesForFinalHash = listOf(
            1 to 0..9, // Document number + hash, second line
            1 to 13..19, // Birth date + hash, second line
            1 to 21..42 // Expiry date + hash + opt + hash, second line
        )
        private val namesRange = 5..43
    }

    override fun parse(lines: List<String>, opt: MrzParserOptions): Mrz {
        val first = lines[0]
        val second = lines[1]
        return MrzBuilder(opt).apply {
            format = MrzFormat.TD3
            documentType = MrzDocumentType.fromMrz(first.extract(docRange))
            countryCode = first.extract(countryRange)
            documentNumber = second.substring(documentNumberRange)
            documentNumberHash = second.extractNumber(documentNumberHashRange)
            optionalData = second.extract(opt1Range)
            birthdate = second.substring(birthDateRange)
            birthdateHash = second.extractNumber(birthdayHashRange)
            sex = MrzSex.fromMrz(second[sexRange])
            expiryDate = second.substring(expiryDateRange)
            expiryDateHash = second.extractNumber(expiryDateHashRange)
            nationalityCountryCode = second.extract(natRange)
            finalHashString = listOfRangesForFinalHash.joinToString("") {
                lines[it.first].substring(it.second)
            }
            finalHash = second.extractNumber(finalHashRange)
            first.extractNames(namesRange).let {
                surnames = it.first
                givenNames = it.second
            }
        }.build()
    }
}
