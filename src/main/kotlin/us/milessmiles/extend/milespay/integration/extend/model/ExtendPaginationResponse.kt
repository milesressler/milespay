package us.milessmiles.extend.milespay.integration.extend.model

/**
 * Pagination details response for Extend API
 */
data class ExtendPaginationResponse(
    val page: Int,
    val pageItemCount: Int,
    val totalItems: Int,
    val numberOfPages: Int,
)
