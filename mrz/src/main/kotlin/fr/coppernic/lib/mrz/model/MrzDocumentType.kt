package fr.coppernic.lib.mrz.model

/**
 * List of supported document types
 */
enum class MrzDocumentType {

    /**
     * A passport, P or IP.
     * ... maybe Travel Document that is very similar to the passport.
     */
    Passport,

    /**
     * General I type (besides IP).
     */
    TypeI,

    /**
     * General A type (besides AC).
     */
    TypeA,

    /**
     * Crew member (AC).
     */
    CrewMember,

    /**
     * General type C.
     */
    TypeC,

    /**
     * Type V (Visa).
     */
    TypeV,

    /**
     *
     */
    Migrant;

    companion object {
        fun fromMrz(mrz: String): MrzDocumentType {
            // 2-letter checks
            return when (mrz) {
                "AC" -> CrewMember
                "ME" -> Migrant
                "TD" -> Migrant // travel document
                "IP" -> Passport
                else -> when (mrz[0]) {
                    'T', 'P' -> Passport
                    'A' -> TypeA
                    'C' -> TypeC
                    'V' -> TypeV
                    'I' -> TypeI // identity card or residence permit
                    'R' -> Migrant // recognised refugee under the 1951 Geneva Convention
                    else -> throw MrzParserException(ErrorType.UnsupportedDocumentType(mrz))
                }
            }
        }
    }
}
