package us.milessmiles.extend.milespay.integration.common

import us.milessmiles.extend.milespay.authentication.model.AuthenticationResponse
import us.milessmiles.extend.milespay.card.model.VirtualCardResponse
import us.milessmiles.extend.milespay.common.model.Page
import us.milessmiles.extend.milespay.transaction.model.TransactionDetailResponse
import us.milessmiles.extend.milespay.transaction.model.TransactionResponse

interface ExternalCardService {
    fun authenticate(username: String, password: String): AuthenticationResponse
    fun getVirtualCards(token: String): Page<VirtualCardResponse>
    fun getTransactions(token: String, card: String): Page<TransactionResponse>
    fun getTransaction(token: String, id: String): TransactionDetailResponse
}
