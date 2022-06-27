package us.milessmiles.extend.milespay.authentication.service

import us.milessmiles.extend.milespay.authentication.model.AuthenticationResponse

interface AuthenticationService {

    fun authenticate(username: String, password: String): AuthenticationResponse

}
