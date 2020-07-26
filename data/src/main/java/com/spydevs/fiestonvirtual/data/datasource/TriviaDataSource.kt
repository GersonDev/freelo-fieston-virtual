package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaRequest
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This data source is used for getting trivia data from a specific data source.
 */
interface TriviaDataSource {
    /**
     * @param [triviaRequest] a request for getting the trivia.
     * @return the [ResultType].
     */
    suspend fun getTrivia(triviaRequest: TriviaRequest): ResultType<List<Trivia>, String>

    /**
     * @param [answerTriviaRequest] a request for answer a trivia.
     * @return the [ResultType].
     */
    suspend fun answerTrivia(
        answerTriviaRequest: AnswerTriviaRequest
    ): ResultType<AnswerTriviaResponse, ErrorResponse>

}