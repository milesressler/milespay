package us.milessmiles.extend.milespay.integration.extend.model

import us.milessmiles.extend.milespay.transaction.model.TransactionResponse

/**
 * Transactions response for Extend API
 */
data class ExtendTransactionsResponse(
    val transactions: List<ExtendTransaction>
)


data class ExtendTransaction(
    val merchantName: String?,
    val status: String?,
    val authedAt: String?,
    val updatedAt: String?,
) {
    fun convert(): TransactionResponse {
        return TransactionResponse(merchantName=merchantName, amount = null, status=null, timestamp = null)
    }
}


