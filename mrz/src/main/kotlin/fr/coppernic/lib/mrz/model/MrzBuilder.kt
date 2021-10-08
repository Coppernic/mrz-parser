package fr.coppernic.lib.mrz.model

import fr.coppernic.lib.mrz.Mrz
import java.util.Date

class MrzBuilder {
    var format: MrzFormat? = null
    var documentType: MrzDocumentType? = null
    var countryCode: String = ""
    var surnames: String = ""
    var givenNames: String = ""
    var documentNumber: String? = ""
    var nationalityCountryCode: String = ""
    var birthdate: Date? = null
    var sex: MrzSex = MrzSex.Unknown
    var expiryDate: Date? = null
    var optionalData: String = ""
    var optionalData2: String = ""

    fun build(): Mrz {
        check()
        return Mrz(
            format = format ?: throw MrzParserException(ErrorType.WrongFormat),
            documentType = documentType ?: throw MrzParserException(ErrorType.WrongFormat),
            countryCode = countryCode,
            surnames = surnames,
            givenNames = givenNames,
            documentNumber = documentNumber,
            nationalityCountryCode = nationalityCountryCode,
            birthdate = birthdate,
            sex = sex,
            expiryDate = expiryDate,
            optionalData = optionalData,
            optionalData2 = optionalData2,
        )
    }

    private fun check() {
        if (format == null) throw MrzParserException(ErrorType.WrongFormat)
    }
}
