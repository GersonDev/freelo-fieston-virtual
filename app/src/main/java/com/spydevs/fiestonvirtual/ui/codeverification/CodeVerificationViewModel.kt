package com.spydevs.fiestonvirtual.ui.codeverification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeVerificationViewModel(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {
    private val _isSuccessCode = MutableLiveData<Boolean>()

    val isSuccessCode: LiveData<Boolean>
        get() = _isSuccessCode

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    fun verifyCode(code: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            if (!code.isNullOrEmpty()) {
                when (val loginResult = loginUserUseCase.invoke(code)) {
                    is ResultType.Success -> {
                        _isSuccessCode.value = true
                    }
                    is ResultType.Error -> {
                        _error.value = loginResult.value
                    }
                }
            }
        }
    }
}