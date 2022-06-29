package us.milessmiles.extend.milespay.transaction.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.milessmiles.extend.milespay.common.model.Page
import us.milessmiles.extend.milespay.integration.common.ExternalCardService
import us.milessmiles.extend.milespay.transaction.model.TransactionDetailResponse
import us.milessmiles.extend.milespay.transaction.model.TransactionResponse

@Service
class DefaultTransactionService: TransactionService {

    @Autowired
    lateinit var externalCardService: ExternalCardService

    override fun getTransactions(token: String, cardId: String): Page<TransactionResponse> {
        return externalCardService.getTransactions(token, cardId)
    }

    override fun getTransactionById(token: String, id: String): TransactionDetailResponse {
        return externalCardService.getTransaction(token, id)
    }


}
