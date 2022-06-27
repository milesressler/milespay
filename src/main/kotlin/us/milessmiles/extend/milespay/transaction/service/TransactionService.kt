package us.milessmiles.extend.milespay.transaction.service

import us.milessmiles.extend.milespay.transaction.model.TransactionResponse

interface TransactionService {
    fun getTransactions(token: String, cardId: String) : List<TransactionResponse>
}
