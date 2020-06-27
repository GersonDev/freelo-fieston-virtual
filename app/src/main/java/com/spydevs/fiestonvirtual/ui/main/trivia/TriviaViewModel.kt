package com.spydevs.fiestonvirtual.ui.main.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.GetTriviaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TriviaViewModel(private val getTriviaUseCase: GetTriviaUseCase) : ViewModel() {

    private val _trivia = MutableLiveData<List<Trivia>>()
    val trivia: LiveData<List<Trivia>>
        get() = _trivia

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getTrivia() {
        viewModelScope.launch(Dispatchers.Main) {

            when (val result = getTriviaUseCase()) {
                is ResultType.Success -> {
                    _trivia.value = result.value
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
        }
    }
}