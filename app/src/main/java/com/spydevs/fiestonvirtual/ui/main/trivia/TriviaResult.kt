package com.spydevs.fiestonvirtual.ui.main.trivia

import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia

sealed class TriviaResult {

    sealed class GetTrivia : TriviaResult() {
        data class Successful(
            val triviaList: List<Trivia>
        ) : GetTrivia()

        data class Error(
            val text: String
        ) : GetTrivia()
    }

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