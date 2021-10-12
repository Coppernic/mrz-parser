package fr.coppernic.lib.mrz.parser.extensions

import fr.coppernic.lib.mrz.model.MrzParserException
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.Test

class StringExtensionsKtTest {

    @Test
    fun extractNames() {
        "PROUT<<POUET".extractNames().`should be equal to`("PROUT" to "POUET")
        "PROUT<<POUET<PIPI<CACA".extractNames().`should be equal to`("PROUT" to "POUET PIPI CACA")
        "PROUT<<POUET<PIPI<CACA<<<<<<<<<<<<<<<".extractNames().`should be equal to`("PROUT" to "POUET PIPI CACA")
    }

    @Test
    fun toNumber() {
        'A'.toNumber().`should be equal to`(10)
        'Z'.toNumber().`should be equal to`(35)
        '0'.toNumber().`should be equal to`(0)
        '9'.toNumber().`should be equal to`(9)
        '<'.toNumber().`should be equal to`(0)
        invoking { 'a'.toNumber() } `should throw` (MrzParserException::class)
    }

    @Test
    fun computeCheckDigit() {
        "520727".computeCheckDigit().`should be equal to`(3)
        "AB2134<<<".computeCheckDigit().`should be equal to`(5)
        "HA672242<658022549601086<<<<<<<<<<<<<<0".computeCheckDigit().`should be equal to`(8)
        (
            "D231458907<<<<<<<<<<<<<<<" +
                "34071279507122<<<<<<<<<<<"
            ).computeCheckDigit().`should be equal to`(2)
    }
}
