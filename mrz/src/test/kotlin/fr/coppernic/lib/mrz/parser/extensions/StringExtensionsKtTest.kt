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

    @Test
    fun divide() {
        "IDFRAAZERTYU<<<<<<<<<<<<<<<<<<5482947621133537444QSDFGHJ<<POIUY1234563M3"
            .divide(2)
            .`should be equal to`(
                listOf(
                    "IDFRAAZERTYU<<<<<<<<<<<<<<<<<<548294",
                    "7621133537444QSDFGHJ<<POIUY1234563M3"
                )
            )
        (
            "I<UTOD231458907<<<<<<<<<<<<<<<" +
                "7408122F1204159UTO<<<<<<<<<<<6" +
                "ERIKSSON<<ANNA<MARIA<<<<<<<<<<"
            )
            .divide(3)
            .`should be equal to`(
                listOf(
                    "I<UTOD231458907<<<<<<<<<<<<<<<",
                    "7408122F1204159UTO<<<<<<<<<<<6",
                    "ERIKSSON<<ANNA<MARIA<<<<<<<<<<"
                )
            )
    }

    @Test
    fun split() {
        (
            "IDFRAAZERTYU<<<<<<<<<<<<<<<<<<548294\r" +
                "7621133537444QSDFGHJ<<POIUY1234563M3\r"
            )
            .separate()
            .`should be equal to`(
                listOf(
                    "IDFRAAZERTYU<<<<<<<<<<<<<<<<<<548294",
                    "7621133537444QSDFGHJ<<POIUY1234563M3"
                )
            )
    }
}
