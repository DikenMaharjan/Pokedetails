package com.example.pokedetails.data.pagination.models.paginatedresponse

data class PaginatedResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)