package com.spydevs.fiestonvirtual.domain.usecases.implementations.trivia

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaRequest
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaResponse
import com.spydevs.fiestonvirtual.domain.repository.TriviaRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.AnswerTriviaUseCase

class AnswerTriviaUseCaseImpl(
    private val triviaRepository: TriviaRepository,
    private val usersRepository: UsersRepository
) : AnswerTriviaUseCase {

    override suspend fun invoke(
        idAlternative: Int
    ): ResultType<AnswerTriviaResponse, ErrorResponse> {
        val user = usersRepository.getLocalUser()
        val result = triviaRepository.answerTrivia(
            AnswerTriviaRequest(idAlternative, user.id)
        )
        if (result is ResultType.Success) {
            usersRepository.updateLocalTotalScoreOfUser(
                user.id,
                result.value.userTotalScore
            )
        }
        return result
    }

}