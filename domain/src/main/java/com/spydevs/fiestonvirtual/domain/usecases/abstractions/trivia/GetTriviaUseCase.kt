package com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * Use case for getting trivia game information.
 */
interface GetTriviaUseCase {
    suspend operator fun invoke(): ResultType<List<Trivia>, ErrorResponse>
}