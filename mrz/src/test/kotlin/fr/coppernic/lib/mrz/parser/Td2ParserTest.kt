package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.log.LogDefines
import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.resources.Resources
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class Td2ParserTest {

    private lateinit var sut: Td2Parser

    @Before
    fun setUp() {
        LogDefines.verbose = true
        sut = Td2Parser()
    }

    @Test
    fun parseTd2() {
        sut.parse(Resources.TD2.split("\n"), MrzParserOptions()).`should be equal to`(
            Mrz(
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
            )
        )
    }
}
