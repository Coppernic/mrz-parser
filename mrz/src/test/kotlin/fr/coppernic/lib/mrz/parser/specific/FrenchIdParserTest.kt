package fr.coppernic.lib.mrz.parser.specific

import fr.coppernic.lib.log.MrzParserDefines
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources
import fr.coppernic.lib.mrz.resources.Resources.mrzFrenchID
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class FrenchIdParserTest {

    private lateinit var sut: FrenchIdParser

    @Before
    fun setUp() {
        MrzParserDefines.verbose = true
        sut = FrenchIdParser()
    }

    @Test
    fun parseFrenchId() {
        sut.parse(Resources.FRENCH_ID.split("\n"), MrzParserOptions()).`should be equal to`(
            mrzFrenchID
        )
    }
}
