package us.milessmiles.extend.milespay.integration.extend.model

import us.milessmiles.extend.milespay.transaction.model.TransactionDetailResponse

/**
 * Transaction response for Extend API
 */
data class ExtendTransactionResponse(
    val merchantName: String,
    val status: String,
    val authedAt: String,
    val updatedAt: String,
    val merchantCity: String,
    val merchantState: String,
) {
    fun convert(): TransactionDetailResponse {
        return TransactionDetailResponse(merchantName=merchantName, amount = null, status=null, timestamp = null,
            location="${merchantCity}, $merchantState")
    }
}


