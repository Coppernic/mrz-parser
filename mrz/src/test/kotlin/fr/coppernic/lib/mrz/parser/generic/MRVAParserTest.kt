package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.log.MrzParserDefines
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources
import fr.coppernic.lib.mrz.resources.Resources.mrzMRVA
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class MRVAParserTest {
    private lateinit var sut: MRVAParser

    @Before
    fun setUp() {
        MrzParserDefines.verbose = true
        sut = MRVAParser()
    }

    @Test
    fun parseMrva() {
        sut.parse(Resources.MRVA.split("\n"), MrzParserOptions()).`should be equal to`(
            mrzMRVA
        )
    }
}
