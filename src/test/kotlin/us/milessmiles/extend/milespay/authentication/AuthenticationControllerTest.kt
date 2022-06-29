package us.milessmiles.extend.milespay.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest {

    @LocalServerPort
    private val port = 0


    @Autowired
    private val webTestClient: WebTestClient? = null

}
