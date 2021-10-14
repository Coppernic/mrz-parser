package fr.coppernic.lib.mrz.resources

import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.generic.Td1ParserTest
import fr.coppernic.lib.mrz.parser.generic.Td3ParserTest

object Resources {
    val TD1 = """
            I<UTOD231458907<<<<<<<<<<<<<<<
            7408122F1204159UTO<<<<<<<<<<<6
            ERIKSSON<<ANNA<MARIA<<<<<<<<<<
    """.trimIndent()
    val mrzTD1 = Mrz(
        format = MrzFormat.TD1,
        documentType = MrzDocumentType.TypeI,
        countryCode = "UTO",
        surnames = "ERIKSSON",
        givenNames = "ANNA MARIA",
        documentNumber = "D23145890",
        nationalityCountryCode = "UTO",
        birthdate = Td1ParserTest.dateFormat.parse("1974-08-12"),
        sex = MrzSex.Female,
        expiryDate = Td1ParserTest.dateFormat.parse("2012-04-15"),
        optionalData = "",
        optionalData2 = "",
        key = "D23145890774081221204159",
        mrzString = TD1,
    )

    val TD1_WRONG = """
            I<UTOD231458907<<<<<<<<<<<<<<<
            7408122F1204159UTO<<<<<<<<<<<6
            ERIKSSON<<ANNA<MARIA<<<<<<<<
    """.trimIndent()
    val TD2 = """
            I<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<
            D231458907UTO7408122F1204159<<<<<<<6
    """.trimIndent()
    val mrzTD2 = Mrz(
        format = MrzFormat.TD2,
        documentType = MrzDocumentType.TypeI,
        countryCode = "UTO",
        surnames = "ERIKSSON",
        givenNames = "ANNA MARIA",
        documentNumber = "D23145890",
        nationalityCountryCode = "UTO",
        birthdate = Td3ParserTest.dateFormat.parse("1974-08-12"),
        sex = MrzSex.Female,
        expiryDate = Td3ParserTest.dateFormat.parse("2012-04-15"),
        optionalData = "",
        optionalData2 = "",
        key = "D23145890774081221204159",
        mrzString = TD2,
    )

    val TD3 = """
            P<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<<<<<<<<<
            L898902C36UTO7408122F1204159ZE184226B<<<<<10
    """.trimIndent()
    val mrzTD3 = Mrz(
        format = MrzFormat.TD3,
        documentType = MrzDocumentType.Passport,
        countryCode = "UTO",
        surnames = "ERIKSSON",
        givenNames = "ANNA MARIA",
        documentNumber = "L898902C3",
        nationalityCountryCode = "UTO",
        birthdate = Td3ParserTest.dateFormat.parse("1974-08-12"),
        sex = MrzSex.Female,
        expiryDate = Td3ParserTest.dateFormat.parse("2012-04-15"),
        optionalData = "ZE184226B",
        optionalData2 = "",
        key = "L898902C3674081221204159",
        mrzString = TD3,
    )

    val PASSPORT_D = """
            P<UTODOE<<JANE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            2424110103D<<6908083F1208024<<<<<<<<<<<<<<<8
    """.trimIndent()
    val mrzPassportD = Mrz(
        format = MrzFormat.TD3,
        documentType = MrzDocumentType.Passport,
        countryCode = "UTO",
        surnames = "DOE",
        givenNames = "JANE",
        documentNumber = "242411010",
        nationalityCountryCode = "D",
        birthdate = Td3ParserTest.dateFormat.parse("1969-08-08"),
        sex = MrzSex.Female,
        expiryDate = Td3ParserTest.dateFormat.parse("2012-08-02"),
        optionalData = "",
        optionalData2 = "",
        // Issue in hash
        expiryDateHashValid = false,
        finalHashValid = false,
        key = "242411010369080831208024",
        mrzString = PASSPORT_D,
    )

    val PASSPORT_R = """
            P<RUSIMIAREK<<EVGENII<<<<<<<<<<<<<<<<<<<<<<<
            1104000008RUS8209120M2601157<<<<<<<<<<<<<<06
    """.trimIndent()
    val mrzPassportR = Mrz(
        format = MrzFormat.TD3,
        documentType = MrzDocumentType.Passport,
        countryCode = "RUS",
        surnames = "IMIAREK",
        givenNames = "EVGENII",
        documentNumber = "110400000",
        birthdate = Td3ParserTest.dateFormat.parse("1982-09-12"),
        sex = MrzSex.Male,
        expiryDate = Td3ParserTest.dateFormat.parse("2026-01-15"),
        optionalData = "",
        optionalData2 = "",
        nationalityCountryCode = "RUS",
        key = "110400000882091202601157",
        mrzString = PASSPORT_R,
    )

    val RUSSIAN_PASSPORT = """
            PNRUSZDRIL7K<<SERGEQ<ANATOL9EVI3<<<<<<<<<<<<
            3919353498RUS7207233M<<<<<<<4151218910003<50
    """.trimIndent()
    val mrzRussianPassport = Mrz(
        format = MrzFormat.TD3,
        documentType = MrzDocumentType.Passport,
        countryCode = "RUS",
        surnames = "ZDRIL7K",
        givenNames = "SERGEQ ANATOL9EVI3",
        documentNumber = "391935349",
        birthdate = Td3ParserTest.dateFormat.parse("1972-07-23"),
        sex = MrzSex.Male,
        expiryDate = null,
        optionalData = "4151218910003",
        optionalData2 = "",
        nationalityCountryCode = "RUS",
        key = "39193534987207233<<<<<<0",
        mrzString = RUSSIAN_PASSPORT,
    )

    val NETHERLAND_PASSPORT = """
            P<NLDDE<BRUIJN<<WILLEKE<LISELOTTE<<<<<<<<<<<
            SPECI20142NLD6503101F2403096999999990<<<<<84
    """.trimIndent()
    val mrzNetherlandPassport = Mrz(
        format = MrzFormat.TD3,
        documentType = MrzDocumentType.Passport,
        countryCode = "NLD",
        surnames = "DE BRUIJN",
        givenNames = "WILLEKE LISELOTTE",
        documentNumber = "SPECI2014",
        nationalityCountryCode = "NLD",
        birthdate = Td3ParserTest.dateFormat.parse("1965-03-10"),
        sex = MrzSex.Female,
        expiryDate = Td3ParserTest.dateFormat.parse("2024-03-09"),
        optionalData = "999999990",
        optionalData2 = "",
        key = "SPECI2014265031012403096",
        mrzString = NETHERLAND_PASSPORT,
    )

    val MRVA = """
            V<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<<<<<<<<<
            L8988901C4XXX4009078F96121096ZE184226B<<<<<<
    """.trimIndent()
    val mrzMRVA = Mrz(
        format = MrzFormat.MRVA,
        documentType = MrzDocumentType.TypeV,
        countryCode = "UTO",
        surnames = "ERIKSSON",
        givenNames = "ANNA MARIA",
        documentNumber = "L8988901C",
        nationalityCountryCode = "XXX",
        birthdate = Td3ParserTest.dateFormat.parse("1940-09-07"),
        sex = MrzSex.Female,
        expiryDate = Td3ParserTest.dateFormat.parse("1996-12-10"),
        optionalData = "6ZE184226B",
        optionalData2 = "",
        key = "L8988901C440090789612109",
        mrzString = MRVA,
    )

    val MRVB = """
            V<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<
            L8988901C4XXX4009078F9612109<<<<<<<<
    """.trimIndent()
    val mrzMRVB = Mrz(
        format = MrzFormat.MRVB,
        documentType = MrzDocumentType.TypeV,
        countryCode = "UTO",
        surnames = "ERIKSSON",
        givenNames = "ANNA MARIA",
        documentNumber = "L8988901C",
        nationalityCountryCode = "XXX",
        birthdate = Td3ParserTest.dateFormat.parse("1940-09-07"),
        sex = MrzSex.Female,
        expiryDate = Td3ParserTest.dateFormat.parse("1996-12-10"),
        optionalData = "",
        optionalData2 = "",
        key = "L8988901C440090789612109",
        mrzString = MRVB,
    )

    val FRENCH_ID = """
        IDFRABERTHIER<<<<<<<<<<<<<<<<<<<<<<<
        8806923102858CORINNE<<<<<<<6512068F6
    """.trimIndent()
    val mrzFrenchID = Mrz(
        format = MrzFormat.FRENCH_ID,
        documentType = MrzDocumentType.TypeI,
        countryCode = "FRA",
        surnames = "BERTHIER",
        givenNames = "CORINNE",
        documentNumber = "880692310285",
        birthdate = Td3ParserTest.dateFormat.parse("1965-12-06"),
        sex = MrzSex.Female,
        optionalData = "",
        optionalData2 = "",
        expiryDate = null,
        nationalityCountryCode = "",
        key = "880692310285865120680",
        mrzString = FRENCH_ID,
    )

    val TD3_WRONG = """
            P<UTOE*IKSSON<<ANNA<MARIA<<<<<<<<<<<<<<<<<<<
            L898*02C36UTO740*122F120*159ZE184226B<<<<<10
    """.trimIndent()
    val mrzTD3Wrong = Mrz(
        format = MrzFormat.TD3,
        documentType = MrzDocumentType.Passport,
        countryCode = "UTO",
        surnames = "E*IKSSON",
        givenNames = "ANNA MARIA",
        documentNumber = "L898*02C3",
        nationalityCountryCode = "UTO",
        birthdate = null,
        sex = MrzSex.Female,
        expiryDate = null,
        optionalData = "ZE184226B",
        optionalData2 = "",
        key = "L898*02C36740*122120*159",
        documentNumberHashValid = false,
        birthdateHashValid = false,
        expiryDateHashValid = false,
        finalHashValid = false,
        mrzString = TD3_WRONG,
    )

    val FRENCH_ID2 = """
        IDFRANEYRIER<<<<<<<<<<<<<<<<<<013029
        2101133537444MICHEL<<JEAN<<8505279M1
    """.trimIndent()
    val mrzFrenchID2 = Mrz(
        format = MrzFormat.FRENCH_ID,
        documentType = MrzDocumentType.TypeI,
        countryCode = "FRA",
        surnames = "NEYRIER",
        givenNames = "MICHEL JEAN",
        documentNumber = "210113353744",
        birthdate = Td3ParserTest.dateFormat.parse("1985-05-27"),
        sex = MrzSex.Male,
        optionalData = "013029",
        optionalData2 = "",
        expiryDate = null,
        nationalityCountryCode = "",
        key = "210113353744485052790",
        mrzString = FRENCH_ID2,
    )

    val FRENCH_ID3 = (
        "IDFRANEYRIER<<<<<<<<<<<<<<<<<<013029\r" +
            "2101133537444MICHEL<<JEAN<<8505279M1\r"
        )
    val mrzFrenchID3 = Mrz(
        format = MrzFormat.FRENCH_ID,
        documentType = MrzDocumentType.TypeI,
        countryCode = "FRA",
        surnames = "NEYRIER",
        givenNames = "MICHEL JEAN",
        documentNumber = "210113353744",
        birthdate = Td3ParserTest.dateFormat.parse("1985-05-27"),
        sex = MrzSex.Male,
        optionalData = "013029",
        optionalData2 = "",
        expiryDate = null,
        nationalityCountryCode = "",
        key = "210113353744485052790",
        mrzString = FRENCH_ID2,
    )
}
