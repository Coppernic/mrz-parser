package fr.coppernic.lib.mrz.parser.builder

import fr.coppernic.lib.log.LogDefines
import fr.coppernic.lib.log.LogDefines.LOG
import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.ErrorType
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.parser.extensions.DateExtensions
import fr.coppernic.lib.mrz.parser.extensions.computeCheckDigit
import fr.coppernic.lib.mrz.parser.extensions.extractDate
import fr.coppernic.lib.mrz.parser.extensions.sanitize

class MrzBuilder(
    val opt: MrzParserOptions
) {
    var format: MrzFormat? = null
    var documentType: MrzDocumentType? = null
    var countryCode: String = ""
    var surnames: String = ""
    var givenNames: String = ""
    var documentNumber: String = ""
    var documentNumberHash: Int = 0
    var nationalityCountryCode: String = ""
    var birthdate: String = ""
    var birthdateHash: Int = 0
    var sex: MrzSex = MrzSex.Unknown
    var expiryDate: String = ""
    var expiryDateHash: Int = 0
    var optionalData: String = ""
    var optionalData2: String = ""
    var finalHashString: String = ""
    var finalHash: Int = 0

    fun build(): Mrz {
        return Mrz(
            format = format ?: throw MrzParserException(ErrorType.WrongFormat()),
            documentType = documentType ?: throw MrzParserException(ErrorType.WrongFormat()),
            countryCode = countryCode.sanitize(),
            surnames = surnames,
            givenNames = givenNames,
            documentNumber = documentNumber.sanitize(),
            documentNumberHashValid = checkHash(documentNumber, documentNumberHash, "documentNumber", opt),
            nationalityCountryCode = nationalityCountryCode.sanitize(),
            birthdate = birthdate.extractDate(DateExtensions.mrzDateFormat),
            birthdateHashValid = checkHash(birthdate, birthdateHash, "birthdate", opt),
            sex = sex,
            expiryDate = expiryDate.extractDate(DateExtensions.mrzDateExpiryFormat),
            expiryDateHashValid = checkHash(expiryDate, expiryDateHash, "expiryDate", opt),
            optionalData = optionalData.sanitize(),
            optionalData2 = optionalData2.sanitize(),
            finalHashValid = checkHash(finalHashString, finalHash, "finalHash", opt),
            key = "$documentNumber${documentNumber.computeCheckDigit(opt.lenient)}" +
                "$birthdate${birthdate.computeCheckDigit(opt.lenient)}" +
                "$expiryDate${expiryDate.computeCheckDigit(opt.lenient)}"
        )
    }

    private fun checkHash(s: String, expected: Int, partName: String, opt: MrzParserOptions): Boolean {
        val actual = s.computeCheckDigit(opt.lenient)
        return (expected == actual).also {
            if (!it) {
                if (LogDefines.verbose) {
                    LOG.trace("Computing hash for $partName=$s : Expecting $expected, computed $actual")
                }
                if (opt.lenient) {
                    LOG.warn(ErrorType.WrongHash(expected, actual, partName).message)
                } else {
                    throw MrzParserException(ErrorType.WrongHash(expected, actual, partName))
                }
            }
        }
    }
}
