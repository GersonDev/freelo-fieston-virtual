package com.spydevs.fiestonvirtual.data.datasource

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
}