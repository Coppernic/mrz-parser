package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.parser.generic.Td1ParserTest
import fr.coppernic.lib.mrz.parser.generic.Td3ParserTest
import fr.coppernic.lib.mrz.resources.Resources.FRENCH_ID
import fr.coppernic.lib.mrz.resources.Resources.MRVA
import fr.coppernic.lib.mrz.resources.Resources.MRVB
import fr.coppernic.lib.mrz.resources.Resources.TD1
import fr.coppernic.lib.mrz.resources.Resources.TD1_WRONG
import fr.coppernic.lib.mrz.resources.Resources.TD2
import fr.coppernic.lib.mrz.resources.Resources.TD3
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be null`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class MrzParserTest {

    private lateinit var sut: MrzParser

    @Before
    fun before() {
        sut = MrzParser()
    }

    @Test
    fun `parse empty string should give null`() {
        sut.parseOrNull("").`should be null`()
        sut.parseLinesOrNull(listOf("", "", "")).`should be null`()
        invoking { sut.parse("") }.`should throw`(MrzParserException::class)
        invoking { sut.parseLines(listOf("", "", "")) }.`should throw`(MrzParserException::class)
    }

    @Test
    fun getFormat() {
        sut.getFormat(TD1.split("\n")).`should be equal to`(MrzFormat.TD1)
        sut.getFormat(TD2.split("\n")).`should be equal to`(MrzFormat.TD2)
        sut.getFormat(TD3.split("\n")).`should be equal to`(MrzFormat.TD3)
        invoking { sut.getFormat(listOf()) }.`should throw`(MrzParserException::class)
        invoking { sut.getFormat(TD1_WRONG.split("\n")) }.`should throw`(MrzParserException::class)
        sut.getFormat(MRVA.split("\n")).`should be equal to`(MrzFormat.MRVA)
        sut.getFormat(MRVB.split("\n")).`should be equal to`(MrzFormat.MRVB)
        sut.getFormat(FRENCH_ID.split("\n")).`should be equal to`(MrzFormat.FRENCH_ID)
    }

    @Test
    fun testTD1() {
        sut.parse(TD1).shouldBeEqualTo(
            Mrz(
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
            )
        )
    }

    @Test
    fun testTD2() {
        sut.parse(TD2).shouldBeEqualTo(
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

    @Test
    fun testTD3() {
        sut.parse(TD3).shouldBeEqualTo(
            Mrz(
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
            )
        )
    }

    @Test
    fun testMRVA() {
        sut.parse(MRVA).shouldBeEqualTo(
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

    @Test
    fun testMRVB() {
        sut.parse(MRVB).shouldBeEqualTo(
            Mrz(
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
            )
        )
    }

    @Test
    fun testFrenchID() {
        sut.parse(FRENCH_ID).shouldBeEqualTo(
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
