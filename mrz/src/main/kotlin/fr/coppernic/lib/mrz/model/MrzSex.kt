package fr.coppernic.lib.mrz.model

enum class MrzSex(
    /**
     * The MRZ character
     */
    private val mrz: Char
) {
    Male('M'),
    Female('F'),
    Unknown('X');

    companion object {
        fun fromMrz(c: Char): MrzSex = when (c) {
            'M' -> Male
            'F' -> Female
            else -> Unknown
        }
    }
}
