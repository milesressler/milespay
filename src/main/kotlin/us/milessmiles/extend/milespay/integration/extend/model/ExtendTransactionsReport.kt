package us.milessmiles.extend.milespay.integration.extend.model

import us.milessmiles.extend.milespay.transaction.model.TransactionResponse
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

var DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH)

data class ExtendTransactionsReportResponse(
    val report:ExtendTransactionsReport,
)

data class ExtendTransactionsReport(
    val count: Int,
    val numPages: Int,
    val page: Int,
    val perPage: Int,
    val transactions: List<ExtendReportTransaction>,
)

data class ExtendReportTransaction(
    val merchantName: String?,
    val status: String?,
    val authedAt: String?,
    val clearedAt: String?,
    val updatedAt: String?,
    val authMerchantAmountCents: Int?,
    val clearingMerchantAmountCents: Int?,
) {
    fun convert(): TransactionResponse {
        val convertedResponse = TransactionResponse(
            merchantName=merchantName,
            amount="",
            status=status,
            timestamp = (if (authedAt != null) DATE_FORMAT.parse(authedAt) else null),)
        if (status == ExtendTransactionStatus.CLEARED) {
            convertedResponse.amount = clearingMerchantAmountCents.toString()
        } else {
            convertedResponse.amount = authMerchantAmountCents.toString()
        }

        return convertedResponse
    }

}
