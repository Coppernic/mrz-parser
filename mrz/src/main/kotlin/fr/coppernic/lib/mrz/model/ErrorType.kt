package fr.coppernic.lib.mrz.model

sealed class ErrorType(
    val message: String
) {
    object Unknown : ErrorType("Unknown error")
    class WrongFormat(
        val msg: String = ""
    ) : ErrorType("Wrong format")
    class UnsupportedDocumentType(
        code: String
    ) : ErrorType("Unsupported document code: $code")
    class WrongHash(
        val expected: Int,
        val actual: Int,
        val partName: String
    ) : ErrorType("Wrong hash for $partName, expected $expected, actual $actual")
}
