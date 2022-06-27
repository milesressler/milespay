package us.milessmiles.extend.milespay.authentication.model

data class AuthenticationResponse(
    val accessToken: String?,
    val refreshToken: String?,
    val firstName: String?,
    val lastName: String?,
    var displayName: String? = null,)

