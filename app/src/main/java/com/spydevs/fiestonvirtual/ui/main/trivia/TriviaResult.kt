package com.spydevs.fiestonvirtual.ui.main.trivia

import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia

sealed class TriviaResult {

    sealed class GetTrivia : TriviaResult() {
        data class Success(
            val triviaList: List<Trivia>
        ) : GetTrivia()

        data class Error(
            val text: String
        ) : GetTrivia()
    }

    sealed class AnswerTrivia : TriviaResult() {
        data class Success(
            val message: String,
            val userTotalScore: Int
        ) : AnswerTrivia()

        data class Error(
            val message: String
        ) : AnswerTrivia()
    }

    data class Loading(
        val show: Boolean
    ) : TriviaResult()

}