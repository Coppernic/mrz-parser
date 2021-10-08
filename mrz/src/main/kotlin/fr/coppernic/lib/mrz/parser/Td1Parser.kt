package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzBuilder
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex

class Td1Parser : DocumentParser() {
    companion object {
        private val docRange = 0..1
        private val countryRange = 2..4
        private val documentNumberRange = 5..13
        private val hash1Range = 4..14
        private val opt1Range = 15..29
        private val birthDateRange = 0..5
        private val hash2Range = 6..6
        private const val sexRange = 7
        private val expiryDateRange = 8..13
        private val hash3Range = 14..14
        private val natRange = 15..17
        private val opt2Range = 18..28
        private val finalHashRange = 29..29
        private val namesRange = 0..29
    }

    override fun parse(lines: List<String>): Mrz {
        val first = lines[0]
        val second = lines[1]
        val third = lines[2]
        return MrzBuilder().apply {
            format = MrzFormat.TD1
            documentType = MrzDocumentType.fromMrz(first.substring(docRange))
            countryCode = first.substring(countryRange)
            documentNumber = first.substring(documentNumberRange)
            optionalData = sanitize(first.substring(opt1Range))
            birthdate = mrzDateFormat.parse(second.substring(birthDateRange))
            sex = MrzSex.fromMrz(second[sexRange])
            expiryDate = mrzDateFormat.parse(second.substring(expiryDateRange))
            nationalityCountryCode = second.substring(natRange)
            optionalData2 = sanitize(second.substring(opt2Range))
            val names = parseNames(third)
            surnames = names.getOrElse(0) { "" }
            givenNames = names.getOrElse(1) { "" }
        }.build()
    }
}
