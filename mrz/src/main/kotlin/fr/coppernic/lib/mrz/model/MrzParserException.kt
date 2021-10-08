package fr.coppernic.lib.mrz.model

class MrzParserException : Exception {
    constructor(type: ErrorType) : super(type.message) {
        errorType = type
    }
    constructor(type: ErrorType, cause: Throwable?) : super(cause) {
        errorType = type
    }

    val errorType: ErrorType
}
