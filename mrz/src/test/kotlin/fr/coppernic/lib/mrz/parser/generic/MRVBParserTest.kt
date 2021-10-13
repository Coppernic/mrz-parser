package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.log.MrzParserDefines
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources.MRVB
import fr.coppernic.lib.mrz.resources.Resources.mrzMRVB
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class MRVBParserTest {
    private lateinit var sut: MRVBParser

    @Before
    fun setUp() {
        MrzParserDefines.verbose = true
        sut = MRVBParser()
    }

    @Test
    fun parseMrvb() {
        sut.parse(MRVB.split("\n"), MrzParserOptions()).`should be equal to`(
            mrzMRVB
        )
    }
}
