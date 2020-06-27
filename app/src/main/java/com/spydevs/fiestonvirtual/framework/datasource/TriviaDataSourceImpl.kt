package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.TriviaDataSource
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.implementations.TriviaMapper

class TriviaDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi): TriviaDataSource {
    override suspend fun getTrivia(triviaRequest: TriviaRequest): ResultType<List<Trivia>, String> {
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
                ResultType.Error(result.error.message ?: "")
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(result.error.message ?: "")
            }
        }
    }
}