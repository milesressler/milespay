package us.milessmiles.extend.milespay.integration.extend.model

/**
 * Login request for Extend API
 */
data class ExtendLoginRequest(
    val email: String,
    val password: String
)
