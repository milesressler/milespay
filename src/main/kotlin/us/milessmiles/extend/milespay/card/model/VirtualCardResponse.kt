package us.milessmiles.extend.milespay.card.model

data class VirtualCardResponse(
    val id: String,
    val balanceCents: Long,
    val name: String,
)
