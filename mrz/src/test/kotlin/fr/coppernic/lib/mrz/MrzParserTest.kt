package fr.coppernic.lib.mrz

import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzParserException
import fr.coppernic.lib.mrz.resources.Resources.TD1
import fr.coppernic.lib.mrz.resources.Resources.TD1_WRONG
import fr.coppernic.lib.mrz.resources.Resources.TD2
import fr.coppernic.lib.mrz.resources.Resources.TD3
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be null`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
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

        // mrz.`should be equal to`(Mrz(""))
    }

    @Test
    fun getFormat() {
        sut.getFormat(TD1.split("\n")).`should be equal to`(MrzFormat.TD1)
        sut.getFormat(TD2.split("\n")).`should be equal to`(MrzFormat.TD2)
        sut.getFormat(TD3.split("\n")).`should be equal to`(MrzFormat.TD3)
        invoking { sut.getFormat(listOf()) }.`should throw`(MrzParserException::class)
        invoking { sut.getFormat(TD1_WRONG.split("\n")) }.`should throw`(MrzParserException::class)
    }
}
