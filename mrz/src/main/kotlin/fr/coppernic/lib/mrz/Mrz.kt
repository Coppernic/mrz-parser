package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import java.util.Date

/**
 * Base class for MRZ
 */
data class Mrz(
    /**
     * Format of MRZ.
     *
     * It is not the same than document type.
     */
    val format: MrzFormat,
    /**
     * Document type, aka Identity card, Passport, Visa, etc.
     */
    val documentType: MrzDocumentType,
    /**
     * Country code gotten from MRZ.
     *
     * It corresponds more or less to alpha-3 country code. There are some exceptions like for German passport.
     */
    val countryCode: String,
    /**
     * Surnames
     */
    val surnames: String,
    /**
     * Given names
     */
    val givenNames: String,
    /**
     * DOcument number
     */
    val documentNumber: String,
    /**
     * Country code gotten from MRZ.
     *
     * It corresponds more or less to alpha-3 country code. There are some exceptions like for German passport.
     */
    val nationalityCountryCode: String,
    /**
     * Date of birth
     *
     * Date is parsed from yyMMdd format. Century is taken in '(-100 year) - (now)' range
     */
    val birthdate: Date?,
    /**
     * Sex
     */
    val sex: MrzSex,
    /**
     * Date of expiry
     *
     * Date is parsed from yyMMdd format. Century is taken in '(-80 year) - (now +20year)' range
     */
    val expiryDate: Date?,
    /**
     * Optional data
     */
    val optionalData: String?,
    /**
     * Optional data
     */
    val optionalData2: String?,
    /**
     * Key used for reading RFID chip
     */
    val key: String,
    /**
     * Original MRZ String
     */
    val mrzString: String,
    /**
     * Result of CRC check for document number
     */
    val documentNumberHashValid: Boolean = true,
    /**
     * Result of CRC check for birth date
     */
    val birthdateHashValid: Boolean = true,
    /**
     * Result of CRC check for expiry date
     */
    val expiryDateHashValid: Boolean = true,
    /**
     * Result of CRC check for final document hash
     */
    val finalHashValid: Boolean = true,
)
