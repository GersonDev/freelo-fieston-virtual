package com.spydevs.fiestonvirtual.ui.main.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.AnswerTriviaUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.GetTriviaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TriviaViewModel(
    private val getTriviaUseCase: GetTriviaUseCase,
    private val answerTriviaUseCase: AnswerTriviaUseCase
) : ViewModel() {

    private val _getTriviaSuccess = MutableLiveData<TriviaResult>()
    val getTriviaSuccess: LiveData<TriviaResult>
        get() = _getTriviaSuccess

    private val _getTriviaError = MutableLiveData<TriviaResult>()
    val getTriviaError: LiveData<TriviaResult>
        get() = _getTriviaError

    private val _answerTriviaSuccess = MutableLiveData<TriviaResult>()
    val answerTriviaSuccess: LiveData<TriviaResult>
        get() = _answerTriviaSuccess

    private val _answerTriviaError = MutableLiveData<TriviaResult>()
    val answerTriviaError: LiveData<TriviaResult>
        get() = _answerTriviaError

    private val _loading = MutableLiveData<TriviaResult>()
    val loading: LiveData<TriviaResult>
        get() = _loading

    fun getTrivia() {
        _loading.value = TriviaResult.Loading(true)
        viewModelScope.launch(Dispatchers.Main) {
            when (val result = getTriviaUseCase()) {
                is ResultType.Success -> {
                    _getTriviaSuccess.value = TriviaResult.GetTrivia.Success(result.value)
                }
                is ResultType.Error -> {
                    _getTriviaError.value = TriviaResult.GetTrivia.Error(result.value.message)
                }
            }
            _loading.value = TriviaResult.Loading(false)
        }
    }

    fun answerTrivia(idAlternative: Int) {
        _loading.value = TriviaResult.Loading(true)
        viewModelScope.launch(Dispatchers.Main) {
            when (val result = answerTriviaUseCase(idAlternative)) {
                is ResultType.Success -> {
                    _answerTriviaSuccess.value =
                        TriviaResult.AnswerTrivia.Success(
                            result.value.message,
                            result.value.userTotalScore
                        )
                }
                is ResultType.Error -> {
                    _answerTriviaError.value = TriviaResult.AnswerTrivia.Error(result.value.message)
                }
            }
            _loading.value = TriviaResult.Loading(false)
        }
    }

}