package fr.coppernic.lib.mrz.parser.specific

import fr.coppernic.lib.log.LogDefines
import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.parser.generic.Td3ParserTest
import fr.coppernic.lib.mrz.resources.Resources
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class FrenchIdParserTest {

    private lateinit var sut: FrenchIdParser

    @Before
    fun setUp() {
        LogDefines.verbose = true
        sut = FrenchIdParser()
    }

    @Test
    fun parseFrenchId() {
        sut.parse(Resources.FRENCH_ID.split("\n"), MrzParserOptions()).`should be equal to`(
            Mrz(
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
            )
        )
    }
}
