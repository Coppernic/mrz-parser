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

class MRVAParserTest {
    private lateinit var sut: MRVAParser

    @Before
    fun setUp() {
        LogDefines.verbose = true
        sut = MRVAParser()
    }

    @Test
    fun parseMrva() {
        sut.parse(Resources.MRVA.split("\n"), MrzParserOptions()).`should be equal to`(
            Mrz(
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
            )
        )
    }
}
