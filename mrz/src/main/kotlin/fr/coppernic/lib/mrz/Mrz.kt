package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import java.util.Date

/**
 * Base class for MRZ
 */
data class Mrz(
    val format: MrzFormat,
    val documentType: MrzDocumentType,
    val countryCode: String,
    val surnames: String,
    val givenNames: String,
    val documentNumber: String,
    val nationalityCountryCode: String,
    val birthdate: Date?,
    val sex: MrzSex,
    val expiryDate: Date?,
    val optionalData: String?,
    val optionalData2: String?,
    val key: String,
    val mrzString: String,
    val documentNumberHashValid: Boolean = true,
    val birthdateHashValid: Boolean = true,
    val expiryDateHashValid: Boolean = true,
    val finalHashValid: Boolean = true,
)
