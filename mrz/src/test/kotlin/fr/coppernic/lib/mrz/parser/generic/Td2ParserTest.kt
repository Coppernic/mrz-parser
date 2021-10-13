package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.log.MrzParserDefines
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources
import fr.coppernic.lib.mrz.resources.Resources.mrzTD2
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class Td2ParserTest {

    private lateinit var sut: Td2Parser

    @Before
    fun setUp() {
        MrzParserDefines.verbose = true
        sut = Td2Parser()
    }

    @Test
    fun parseTd2() {
        sut.parse(Resources.TD2.split("\n"), MrzParserOptions()).`should be equal to`(
            mrzTD2
        )
    }
}
