package us.milessmiles.extend.milespay.transaction.service

import us.milessmiles.extend.milespay.common.model.Page
import us.milessmiles.extend.milespay.transaction.model.TransactionResponse

interface TransactionService {
    fun getTransactions(token: String, cardId: String) : Page<TransactionResponse>
}
