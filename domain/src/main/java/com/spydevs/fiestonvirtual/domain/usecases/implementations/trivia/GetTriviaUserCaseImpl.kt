package com.spydevs.fiestonvirtual.domain.usecases.implementations.trivia

import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.repository.TriviaRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.GetTriviaUseCase

class GetTriviaUserCaseImpl(private val triviaRepository: TriviaRepository) : GetTriviaUseCase {
    override suspend fun invoke(): ResultType<List<Trivia>, String> {
        val triviaRequest = TriviaRequest(3, 1)
        return triviaRepository.getTrivia(triviaRequest)
    }
}