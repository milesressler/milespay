package us.milessmiles.extend.milespay.integration.extend.model

/**
 * Login response for Extend API
 */
data class ExtendLoginResponse(
    val token: String?,
    val refreshToken: String?,
    val user: ExtendUser?,
)

data class ExtendUser(
    val firstName: String?,
    val lastName: String?,
)
