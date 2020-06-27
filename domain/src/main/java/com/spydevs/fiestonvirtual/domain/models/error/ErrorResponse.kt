package com.spydevs.fiestonvirtual.domain.models.error

/**
 * This model is the Error that returns the services.
 */
data class ErrorResponse(
    val code: Int = 0,
    val title: String = "",
    val message: String = ""
)