package com.spydevs.fiestonvirtual.data.entities.response

data class AnswerTriviaResponseEntity(
    val data: Data,
    val message: String
) {
    data class Data(
        val userTotalScore: Int
    )
}