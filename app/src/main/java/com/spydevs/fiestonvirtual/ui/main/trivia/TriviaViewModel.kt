package com.spydevs.fiestonvirtual.ui.main.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.AnswerTriviaUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.GetTriviaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TriviaViewModel(
    private val getTriviaUseCase: GetTriviaUseCase,
    private val answerTriviaUseCase: AnswerTriviaUseCase
) : ViewModel() {

    private val _trivia = MutableLiveData<List<Trivia>>()
    val trivia: LiveData<List<Trivia>>
        get() = _trivia

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _answerTriviaSuccessful = MutableLiveData<TriviaResult>()
    val answerTriviaSuccessful: LiveData<TriviaResult>
        get() = _answerTriviaSuccessful

    private val _answerTriviaError = MutableLiveData<TriviaResult>()
    val answerTriviaError: LiveData<TriviaResult>
        get() = _answerTriviaError

    private val _loading = MutableLiveData<TriviaResult>()
    val loading: LiveData<TriviaResult>
        get() = _loading

    fun getTrivia() {
        viewModelScope.launch(Dispatchers.Main) {
            when (val result = getTriviaUseCase()) {
                is ResultType.Success -> {
                    _trivia.value = result.value
                }
                is ResultType.Error -> {
                    _error.value = result.value.message
                }
            }
        }
    }

    fun answerTrivia(idAlternative: Int) {
        _loading.value = TriviaResult.Loading(true)
        viewModelScope.launch(Dispatchers.Main) {
            when (val result = answerTriviaUseCase(idAlternative)) {
                is ResultType.Success -> {
                    _answerTriviaSuccessful.value =
                        TriviaResult.AnswerTriviaSuccessful(
                            result.value.message,
                            result.value.userTotalScore
                        )
                }
                is ResultType.Error -> {
                    _answerTriviaError.value = TriviaResult.AnswerTriviaError(result.value.message)
                }
            }
            _loading.value = TriviaResult.Loading(false)
        }
    }

}