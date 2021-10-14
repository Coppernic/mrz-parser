package fr.coppernic.lib.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object MrzParserDefines {
    // Log
    private const val TAG: String = "MrzParser"
    val LOG: Logger = LoggerFactory.getLogger(TAG)

    /**
     * True to activate verbose logging in all lib
     */
    var verbose = false

    /**
     * True to activate profiler in all lib
     */
    var profile = false
}
