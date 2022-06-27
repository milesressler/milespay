package us.milessmiles.extend.milespay.authentication.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.milessmiles.extend.milespay.authentication.model.AuthenticationResponse
import us.milessmiles.extend.milespay.integration.common.ExternalCardService

@Service
class DefaultAuthenticationService: AuthenticationService {

    @Autowired
    lateinit var externalAuthenticationService: ExternalCardService

    override fun authenticate(username: String, password: String): AuthenticationResponse {
        val authenticationResponse = externalAuthenticationService.authenticate(username, password)
        authenticationResponse.displayName = listOfNotNull(
            authenticationResponse.firstName,
            authenticationResponse.lastName
        ).joinToString(" ")
        return authenticationResponse

    }
}
