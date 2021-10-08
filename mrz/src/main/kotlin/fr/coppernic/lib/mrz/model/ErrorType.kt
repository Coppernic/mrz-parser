package fr.coppernic.lib.mrz.model

sealed class ErrorType(
    val message: String
) {
    object Unknown : ErrorType("Unknown error")
    object WrongFormat : ErrorType("Wrong format")
    class UnsupportedDocumentType(
        code: String
    ) : ErrorType("Unsupported document code: $code")
}
