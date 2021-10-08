package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.mrz.Mrz
import org.junit.Before

class DocumentParserTest {

    private lateinit var sut: DocumentParser

    @Before
    fun before() {
        sut = object : DocumentParser() {
            override fun parse(lines: List<String>): Mrz {
                TODO("Not yet implemented")
            }
        }
    }
}
