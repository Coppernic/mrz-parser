package fr.coppernic.lib.mrz.parser

import fr.coppernic.lib.log.LogDefines
import fr.coppernic.lib.mrz.Mrz
import fr.coppernic.lib.mrz.model.MrzDocumentType
import fr.coppernic.lib.mrz.model.MrzFormat
import fr.coppernic.lib.mrz.model.MrzSex
import fr.coppernic.lib.mrz.resources.Resources.TD1
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat

class Td1ParserTest {

    private lateinit var sut: Td1Parser

    @Before
    fun setUp() {
        LogDefines.verbose = true
        sut = Td1Parser()
    }

    @Test
    fun parse() {
        sut.parse(TD1.split("\n"), MrzParserOptions()).`should be equal to`(
            Mrz(
                format = MrzFormat.TD1,
                documentType = MrzDocumentType.TypeI,
                countryCode = "UTO",
                surnames = "ERIKSSON",
                givenNames = "ANNA MARIA",
                documentNumber = "D23145890",
                nationalityCountryCode = "UTO",
                birthdate = dateFormat.parse("1974-08-12"),
                sex = MrzSex.Female,
                expiryDate = dateFormat.parse("2012-04-15"),
                optionalData = "",
                optionalData2 = "",
            )
        )
    }

    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }
}
