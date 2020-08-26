package com.spydevs.fiestonvirtual.domain.usecases.implementations.ranking

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingRequest
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingResponse
import com.spydevs.fiestonvirtual.domain.repository.RankingRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.ranking.GetRankingUseCase

class GetRankingUseCaseImpl(
    private val rankingRepository: RankingRepository,
    private val usersRepository: UsersRepository
) : GetRankingUseCase {

    override suspend fun invoke(): ResultType<GetRankingResponse, ErrorResponse> {
        val user = usersRepository.getLocalUser()
        return rankingRepository.getRanking(
            GetRankingRequest(
                idUser = user.id,
                idEvent = user.idEvent
            )
        )
    }

}