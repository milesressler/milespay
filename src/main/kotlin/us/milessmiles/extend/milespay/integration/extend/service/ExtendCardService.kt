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
import us.milessmiles.extend.milespay.integration.extend.model.*
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
            return Page(content = response.virtualCards.map { it.convert() },
                page = response.pagination.page,
                pageSize = response.pagination.pageItemCount,
                total = response.pagination.totalItems)
        }

        throw Exception("Exception message")
    }


    override fun getTransactions(token: String, card: String): Page<TransactionResponse> {
        val url = "${ExtendUrl.TRANSACTIONS_REPORT}?perPage=10&sort=-dateAuthsFirst&virtualCardId=${card}" +
                "&status=${ExtendTransactionStatus.CLEARED}&status=${ExtendTransactionStatus.DECLINED}&status=${ExtendTransactionStatus.PENDING}"
        val response = webClient.get()
            .uri(url)
            .header("Authorization", token)
            .retrieve()
            .bodyToMono(ExtendTransactionsReportResponse::class.java)
            .block()

        response?.let {
            return Page(content = response.report.transactions.map { it.convert() },
                page = response.report.page,
                pageSize = response.report.perPage,
                total = response.report.count)
        }

        throw Exception("Exception message")

    }


}
