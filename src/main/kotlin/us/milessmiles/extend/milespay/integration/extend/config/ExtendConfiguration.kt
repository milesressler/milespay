package us.milessmiles.extend.milespay.integration.extend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import java.util.*


@Configuration
class ExtendConfiguration {

    @Value("\${extend.api.url}")
    lateinit var EXTEND_API_URL: String

    @Value("\${extend.api.version}")
    lateinit var EXTEND_API_VERSION: String

    @Bean(name= ["ExtendWebClient"])
    fun getWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl(EXTEND_API_URL)
            .defaultHeader(HttpHeaders.ACCEPT, EXTEND_API_VERSION)
            .build()
    }
}
