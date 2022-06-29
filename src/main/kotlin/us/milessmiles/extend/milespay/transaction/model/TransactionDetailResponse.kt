package us.milessmiles.extend.milespay.transaction.model

import java.util.*



data class TransactionDetailResponse(
    var merchantName: String?,
    var amount: String?,
    var status: String?,
    var timestamp: Date?,
    var location: String?){
}
