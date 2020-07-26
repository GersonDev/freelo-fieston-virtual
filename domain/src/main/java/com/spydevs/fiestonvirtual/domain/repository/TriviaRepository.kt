package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaRequest
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This repository is used for interacting with information about everything
 * related to trivia game that comes from whatever data source.
 */
interface TriviaRepository {

    /**
     * Getting trivia game information from some data source.
     * @param [triviaRequest] the request model for getting trivia information.
     * @return [ResultType] The result type with trivia game from the server.
     */
    suspend fun getTrivia(triviaRequest: TriviaRequest): ResultType<List<Trivia>, ErrorResponse>

    /**
     * This is used for answer a trivia.
     * @param [answerTriviaRequest] a request for answer a trivia.
     * @return the [ResultType].
     */
    suspend fun answerTrivia(
        answerTriviaRequest: AnswerTriviaRequest
    ): ResultType<AnswerTriviaResponse, ErrorResponse>

}