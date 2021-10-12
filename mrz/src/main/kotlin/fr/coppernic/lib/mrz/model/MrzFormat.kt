package fr.coppernic.lib.mrz.model

enum class MrzFormat(
    val lineCount: Int,
    val lineLen: Int,
    val type: String = ""
) {
    TD1(3, 30),
    TD2(2, 36),
    TD3(2, 44),
    MRVA(2, 44, "V"),
    MRVB(2, 36, "V"),
    FRENCH_ID(2, 36, "IDFRA")
}
