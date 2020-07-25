package com.spydevs.fiestonvirtual.domain.usecases.implementations.trivia

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.repository.TriviaRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.GetTriviaUseCase

class GetTriviaUseCaseImpl(
    private val triviaRepository: TriviaRepository,
    private val usersRepository: UsersRepository
) : GetTriviaUseCase {
    override suspend fun invoke(): ResultType<List<Trivia>, ErrorResponse> {
        val user = usersRepository.getLocalUser()
        val triviaRequest = TriviaRequest(
            user.idEvent,
            1,
            user.id
        )
        return triviaRepository.getTrivia(triviaRequest)
    }
}