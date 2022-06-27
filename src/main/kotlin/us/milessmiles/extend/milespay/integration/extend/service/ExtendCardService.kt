package us.milessmiles.extend.milespay.integration.extend.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import us.milessmiles.extend.milespay.authentication.model.AuthenticationResponse
import us.milessmiles.extend.milespay.card.model.VirtualCardResponse
import us.milessmiles.extend.milespay.common.model.Page
import us.milessmiles.extend.milespay.integration.common.ExternalCardService
import us.milessmiles.extend.milespay.integration.extend.config.ExtendUrl
import us.milessmiles.extend.milespay.integration.extend.model.ExtendLoginRequest
import us.milessmiles.extend.milespay.integration.extend.model.ExtendLoginResponse
import us.milessmiles.extend.milespay.integration.extend.model.ExtendTransactionsResponse
import us.milessmiles.extend.milespay.integration.extend.model.ExtendVirtualCardsResponse
import us.milessmiles.extend.milespay.transaction.model.TransactionResponse

@Service
class ExtendCardService: ExternalCardService {

    @Autowired
    @Qualifier("ExtendWebClient")
    lateinit var webClient: WebClient

    private val logger: Logger = LoggerFactory.getLogger(ExtendCardService::class.java)

    override fun authenticate(username: String, password: String): AuthenticationResponse {
        val extendLoginRequest = ExtendLoginRequest(username, password)

        val response = webClient.post()
            .uri(ExtendUrl.SIGNIN)
            .body(BodyInserters.fromValue(extendLoginRequest))
            .retrieve()
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(ResponseStatusException(HttpStatus.BAD_GATEWAY, "Extend API exception - try again later")) }
            .onStatus(HttpStatus::is4xxClientError) { err -> Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request.")) }
            .bodyToMono(ExtendLoginResponse::class.java)
            .block()

        response?.let {
            return AuthenticationResponse(accessToken = response.token,
                refreshToken = response.refreshToken,
                firstName = response.user?.firstName, lastName = response.user?.lastName ) }

        throw Exception("Exception message")

    }

    override fun getVirtualCards(token: String): Page<VirtualCardResponse> {
        val response = webClient.get()
            .uri(ExtendUrl.VIRTUAL_CARDS)
            .header("Authorization", token)
            .retrieve()
            .bodyToMono(ExtendVirtualCardsResponse::class.java)
            .block()

        response?.let {
            return Page(content = response.extendVirtualCards.map { it.convert() },
                page = response.pagination.page,
                pageSize = response.pagination.pageItemCount,
                total = response.pagination.totalItems)
        }

        throw Exception("Exception message")
    }


    override fun getTransactions(token: String, card: String): List<TransactionResponse> {
        val response = webClient.get()
            .uri(ExtendUrl.VIRTUAL_CARD_TRANSACTIONS, card)
            .header("Authorization", token)
            .retrieve()
            .bodyToMono(ExtendTransactionsResponse::class.java)
            .block()

        response?.let {
            return response.transactions.map { it.convert() }
        }

        throw Exception("Exception message")

    }


}
