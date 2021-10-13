package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.log.MrzParserDefines
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources
import fr.coppernic.lib.mrz.resources.Resources.TD1
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat

class Td1ParserTest {

    private lateinit var sut: Td1Parser

    @Before
    fun setUp() {
        MrzParserDefines.verbose = true
        sut = Td1Parser()
    }

    @Test
    fun parse() {
        sut.parse(TD1.split("\n"), MrzParserOptions()).`should be equal to`(
            Resources.mrzTD1
        )
    }

    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }
}
