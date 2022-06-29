package us.milessmiles.extend.milespay.integration.extend.model

import us.milessmiles.extend.milespay.card.model.VirtualCardResponse

/**
 * Virtual cards response for Extend API
 */
data class ExtendVirtualCardsResponse(
    val virtualCards: List<ExtendVirtualCard>,
    val pagination: ExtendPaginationResponse,
)

data class ExtendVirtualCard(
    val id: String,
    val status: String,
    val balanceCents: Long,
){
    fun convert(): VirtualCardResponse {
        return VirtualCardResponse(id = this.id)
    }
}


