package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.log.LogDefines
import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat

class Td3ParserTest {

    private lateinit var sut: Td3Parser

    @Before
    fun setUp() {
        LogDefines.verbose = true
        sut = Td3Parser()
    }

    @Test
    fun parseTd3() {
        sut.parse(Resources.TD3.split("\n"), MrzParserOptions()).`should be equal to`(
            Mrz(
                format = MrzFormat.TD3,
                documentType = MrzDocumentType.Passport,
                countryCode = "UTO",
                surnames = "ERIKSSON",
                givenNames = "ANNA MARIA",
                documentNumber = "L898902C3",
                nationalityCountryCode = "UTO",
                birthdate = dateFormat.parse("1974-08-12"),
                sex = MrzSex.Female,
                expiryDate = dateFormat.parse("2012-04-15"),
                optionalData = "ZE184226B",
                optionalData2 = "",
            )
        )
    }

    @Test
    fun parsePassportD() {
        sut.parse(
            Resources.PASSPORT_D.split("\n"),
            MrzParserOptions(lenient = true) // Set lenient because this document has an issue in hash
        ).`should be equal to`(
            Mrz(
                format = MrzFormat.TD3,
                documentType = MrzDocumentType.Passport,
                countryCode = "UTO",
                surnames = "DOE",
                givenNames = "JANE",
                documentNumber = "242411010",
                nationalityCountryCode = "D",
                birthdate = dateFormat.parse("1969-08-08"),
                sex = MrzSex.Female,
                expiryDate = dateFormat.parse("2012-08-02"),
                optionalData = "",
                optionalData2 = "",
                // Issue in hash
                expiryDateHashValid = false,
                finalHashValid = false
            )
        )
    }

    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }
}
