package fr.coppernic.lib.mrz.model

import org.amshove.kluent.`should be`
import org.junit.Test

class MrzSexTest {

    @Test
    fun fromMrz() {
        MrzSex.fromMrz('M').`should be`(MrzSex.Male)
        MrzSex.fromMrz('F').`should be`(MrzSex.Female)
        MrzSex.fromMrz('X').`should be`(MrzSex.Unknown)
        MrzSex.fromMrz('A').`should be`(MrzSex.Unknown)
    }
}
