package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources.FRENCH_ID
import fr.coppernic.lib.mrz.resources.Resources.FRENCH_ID2
import fr.coppernic.lib.mrz.resources.Resources.MRVA
import fr.coppernic.lib.mrz.resources.Resources.MRVB
import fr.coppernic.lib.mrz.resources.Resources.NETHERLAND_PASSPORT
import fr.coppernic.lib.mrz.resources.Resources.PASSPORT_R
import fr.coppernic.lib.mrz.resources.Resources.RUSSIAN_PASSPORT
import fr.coppernic.lib.mrz.resources.Resources.TD1
import fr.coppernic.lib.mrz.resources.Resources.TD1_WRONG
import fr.coppernic.lib.mrz.resources.Resources.TD2
import fr.coppernic.lib.mrz.resources.Resources.TD3
import fr.coppernic.lib.mrz.resources.Resources.TD3_WRONG
import fr.coppernic.lib.mrz.resources.Resources.mrzFrenchID
import fr.coppernic.lib.mrz.resources.Resources.mrzFrenchID2
import fr.coppernic.lib.mrz.resources.Resources.mrzMRVA
import fr.coppernic.lib.mrz.resources.Resources.mrzMRVB
import fr.coppernic.lib.mrz.resources.Resources.mrzNetherlandPassport
import fr.coppernic.lib.mrz.resources.Resources.mrzPassportR
import fr.coppernic.lib.mrz.resources.Resources.mrzRussianPassport
import fr.coppernic.lib.mrz.resources.Resources.mrzTD1
import fr.coppernic.lib.mrz.resources.Resources.mrzTD2
import fr.coppernic.lib.mrz.resources.Resources.mrzTD3
import fr.coppernic.lib.mrz.resources.Resources.mrzTD3Wrong
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
        sut.parse(TD1).shouldBeEqualTo(mrzTD1)
    }

    @Test
    fun testTD2() {
        sut.parse(TD2).shouldBeEqualTo(mrzTD2)
    }

    @Test
    fun testTD3() {
        sut.parse(TD3).shouldBeEqualTo(mrzTD3)
    }

    @Test
    fun testMRVA() {
        sut.parse(MRVA).shouldBeEqualTo(mrzMRVA)
    }

    @Test
    fun testMRVB() {
        sut.parse(MRVB).shouldBeEqualTo(mrzMRVB)
    }

    @Test
    fun testFrenchID() {
        sut.parse(FRENCH_ID).shouldBeEqualTo(mrzFrenchID)
    }

    @Test
    fun testFrenchID2() {
        sut.parse(FRENCH_ID2).shouldBeEqualTo(mrzFrenchID2)
    }

    @Test
    fun testRussian() {
        sut.parse(PASSPORT_R).shouldBeEqualTo(mrzPassportR)
    }

    @Test
    fun testRussian2() {
        sut.parse(RUSSIAN_PASSPORT).shouldBeEqualTo(mrzRussianPassport)
    }

    @Test
    fun testNetherlands() {
        sut.parse(NETHERLAND_PASSPORT).shouldBeEqualTo(mrzNetherlandPassport)
    }

    @Test
    fun testLenientParsing() {
        sut.parse(TD3_WRONG, MrzParserOptions(lenient = true)).shouldBeEqualTo(mrzTD3Wrong)
    }

    @Test
    fun testTD1SingleLine() {
        val line = TD1.trim().replace("\n", "")
        sut.parse(line).`should be equal to`(mrzTD1)
    }

    @Test
    fun testTD12SingleLine() {
        val line = TD2.trim().replace("\n", "")
        sut.parse(line).`should be equal to`(mrzTD2)
    }

    @Test
    fun testTD3SingleLine() {
        val line = TD3.trim().replace("\n", "")
        sut.parse(line).`should be equal to`(mrzTD3)
    }
}
