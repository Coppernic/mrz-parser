package fr.coppernic.lib.mrz.resources

object Resources {
    val TD1 = """
            I<UTOD231458907<<<<<<<<<<<<<<<
            7408122F1204159UTO<<<<<<<<<<<6
            ERIKSSON<<ANNA<MARIA<<<<<<<<<<
    """.trimIndent()
    val TD1_WRONG = """
            I<UTOD231458907<<<<<<<<<<<<<<<
            7408122F1204159UTO<<<<<<<<<<<6
            ERIKSSON<<ANNA<MARIA<<<<<<<<
    """.trimIndent()
    val TD2 = """
            I<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<
            D231458907UTO7408122F1204159<<<<<<<6
    """.trimIndent()
    val TD3 = """
            P<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<<<<<<<<<
            L898902C36UTO7408122F1204159ZE184226B<<<<<10
    """.trimIndent()
    val PASSPORT_D = """
            P<UTODOE<<JANE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            2424110103D<<6908083F1208024<<<<<<<<<<<<<<<8
    """.trimIndent()
    val PASSPORT_R = """
            P<RUSIMIAREK<<EVGENII<<<<<<<<<<<<<<<<<<<<<<<
            1104000008RUS8209120M2601157<<<<<<<<<<<<<<06
    """.trimIndent()
    val RUSSIAN_PASSPORT = """
            PNRUSZDRIL7K<<SERGEQ<ANATOL9EVI3<<<<<<<<<<<<
            3919353498RUS7207233M<<<<<<<4151218910003<50
    """.trimIndent()
    val NETHERLAND_PASSPORT = """
            P<NLDDE<BRUIJN<<WILLEKE<LISELOTTE<<<<<<<<<<<
            SPECI20142NLD6503101F2403096999999990<<<<<84
    """.trimIndent()
    val MRVA = """
            V<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<<<<<<<<<
            L8988901C4XXX4009078F96121096ZE184226B<<<<<<
    """.trimIndent()
    val MRVB = """
            V<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<
            L8988901C4XXX4009078F9612109<<<<<<<<
    """.trimIndent()
}
