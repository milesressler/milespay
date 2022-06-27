package us.milessmiles.extend.milespay.common.model

data class Page<T>(
    val page: Int,
    val pageSize: Int,
    val total: Int,
    val content: List<T>)
