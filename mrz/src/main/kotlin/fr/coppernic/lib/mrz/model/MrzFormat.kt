package fr.coppernic.lib.mrz.model

enum class MrzFormat(
    val lineCount: Int,
    val lineLen: Int,
) {
    TD1(3, 30),
    TD2(2, 36),
    TD3(2, 44),
    MRVA(2, 44),
    MRVB(2, 36),
    FRENCH_ID(2, 36)
}
