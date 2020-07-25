package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.TriviaDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaRequest
import com.spydevs.fiestonvirtual.domain.models.trivia.AnswerTriviaResponse
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.implementations.TriviaMapper

class TriviaDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi) : TriviaDataSource {
    override suspend fun getTrivia(triviaRequest: TriviaRequest): ResultType<List<Trivia>, ErrorResponse> {
        return when (val result = fiestonVirtualApi.getTrivia(triviaRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    TriviaMapper.convertFromInitial(result.body)
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
        }
    }

    override suspend fun answerTrivia(
        answerTriviaRequest: AnswerTriviaRequest
    ): ResultType<AnswerTriviaResponse, ErrorResponse> {
        return when (val result = fiestonVirtualApi.answerTrivia(answerTriviaRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    AnswerTriviaResponse(
                        result.body.message,
                        result.body.data.userTotalScore
                    )
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
        }
    }
}