package us.milessmiles.extend.milespay.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import us.milessmiles.extend.milespay.authentication.model.AuthenticationRequest
import us.milessmiles.extend.milespay.authentication.model.AuthenticationResponse
import us.milessmiles.extend.milespay.authentication.service.AuthenticationService


@RestController
class AuthenticationController {

    @Autowired
    lateinit var authenticationService: AuthenticationService

    @PostMapping("/authenticate")
    fun signin(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
        return authenticationService.authenticate(request.username, request.password)
    }

}
