package com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case is used for answer a trivia.
 */
interface AnswerTriviaUseCase {

    suspend operator fun invoke(
        idAlternative: Int
    ): ResultType<AnswerTriviaResponse, ErrorResponse>

}