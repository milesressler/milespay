package us.milessmiles.extend.milespay.transaction.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import us.milessmiles.extend.milespay.common.model.Page
import us.milessmiles.extend.milespay.transaction.model.TransactionDetailResponse
import us.milessmiles.extend.milespay.transaction.model.TransactionResponse
import us.milessmiles.extend.milespay.transaction.service.TransactionService

@RestController
class TransactionController {

    @Autowired
    lateinit var transactionService: TransactionService

    @CrossOrigin
    @GetMapping("card/{cardId}/transaction")
    fun getTransactionsForCard(@RequestHeader("Authorization") authHeader: String,
            @PathVariable(name="cardId") cardId: String): Page<TransactionResponse> {
        return transactionService.getTransactions(authHeader, cardId)
    }

    @CrossOrigin
    @GetMapping("transaction/{id}")
    fun getTransactionById(@RequestHeader("Authorization") authHeader: String,
            @PathVariable(name="id") txId: String): TransactionDetailResponse {
        return transactionService.getTransactionById(authHeader, txId)
    }
}
