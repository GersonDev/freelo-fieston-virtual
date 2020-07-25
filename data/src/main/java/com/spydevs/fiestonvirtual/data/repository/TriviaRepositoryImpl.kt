package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.TriviaDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaRequest
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.repository.TriviaRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class TriviaRepositoryImpl(
    private val triviaDataSource: TriviaDataSource
) : TriviaRepository {
    override suspend fun getTrivia(triviaRequest: TriviaRequest): ResultType<List<Trivia>, ErrorResponse> {
        return triviaDataSource.getTrivia(triviaRequest)
    }

    override suspend fun answerTrivia(
        answerTriviaRequest: AnswerTriviaRequest
    ): ResultType<AnswerTriviaResponse, ErrorResponse> {
        return triviaDataSource.answerTrivia(answerTriviaRequest)
    }
}