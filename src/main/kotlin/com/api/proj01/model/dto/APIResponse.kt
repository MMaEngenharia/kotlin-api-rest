package com.api.proj01.model.dto

data class APIResponse(
    var data: Any? = "",
    val page: Int = 1,
    val totalPages: Int = 1,
    val size: Int = 0,
    val message: String = "Requisição executada com sucesso"
)