package com.spydevs.fiestonvirtual.ui.main.trivia

sealed class TriviaResult {

    data class AnswerTriviaSuccessful(
        val message: String,
        val userTotalScore: Int
    ) : TriviaResult()

    data class AnswerTriviaError(
        val message: String
    ) : TriviaResult()

    data class Loading(
        val show: Boolean
    ) : TriviaResult()

}