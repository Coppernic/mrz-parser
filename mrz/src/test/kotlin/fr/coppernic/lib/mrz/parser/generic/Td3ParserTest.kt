package fr.coppernic.lib.mrz.parser.generic

import fr.coppernic.lib.log.MrzParserDefines
import fr.coppernic.lib.mrz.parser.MrzParserOptions
import fr.coppernic.lib.mrz.resources.Resources
import fr.coppernic.lib.mrz.resources.Resources.mrzPassportD
import fr.coppernic.lib.mrz.resources.Resources.mrzTD3
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat

class Td3ParserTest {

    private lateinit var sut: Td3Parser

    @Before
    fun setUp() {
        MrzParserDefines.verbose = true
        sut = Td3Parser()
    }

    @Test
    fun parseTd3() {
        sut.parse(Resources.TD3.split("\n"), MrzParserOptions()).`should be equal to`(
            mrzTD3
        )
    }

    @Test
    fun parsePassportD() {
        sut.parse(
            Resources.PASSPORT_D.split("\n"),
            MrzParserOptions(lenient = true) // Set lenient because this document has an issue in hash
        ).`should be equal to`(
            mrzPassportD
        )
    }

    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }
}
