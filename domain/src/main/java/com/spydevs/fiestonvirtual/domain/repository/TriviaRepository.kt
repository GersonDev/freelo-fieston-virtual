package com.spydevs.fiestonvirtual.domain.repository

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
    suspend fun getTrivia(triviaRequest: TriviaRequest): ResultType<List<Trivia>, String>
}