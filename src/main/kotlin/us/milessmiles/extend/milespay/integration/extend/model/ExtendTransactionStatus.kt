package us.milessmiles.extend.milespay.integration.extend.model

object ExtendTransactionStatus {
    const val PENDING = "PENDING"
    const val CLEARED = "CLEARED"
    const val DECLINED = "DECLINED"
    const val NO_MATCH = "NO_MATCH"
    const val AVS_PASS = "AVS_PASS"
    const val AVS_FAIL = "AVS_FAIL"
    const val AUTH_REVERSAL = "AUTH_REVERSAL"
}
