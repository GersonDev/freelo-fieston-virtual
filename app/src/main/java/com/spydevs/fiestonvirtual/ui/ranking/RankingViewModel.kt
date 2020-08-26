package com.spydevs.fiestonvirtual.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.ranking.GetRankingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RankingViewModel(
    val getRankingUseCase: GetRankingUseCase
) : ViewModel() {

    private val _getRanking = MutableLiveData<RankingResult.GetRanking>()
    val getRanking: LiveData<RankingResult.GetRanking> = _getRanking

    fun getRanking() {
        viewModelScope.launch(Dispatchers.Main) {
            _getRanking.value = RankingResult.GetRanking.Loading(true)
            when (val result = getRankingUseCase()) {
                is ResultType.Success -> {
                    _getRanking.value = RankingResult.GetRanking.Success(
                        result.value.userTotalScore,
                        result.value.ranking
                    )
                }
                is ResultType.Error -> {
                    _getRanking.value = RankingResult.GetRanking.Loading(true)
                }
            }
            _getRanking.value = RankingResult.GetRanking.Loading(false)
        }
    }

}