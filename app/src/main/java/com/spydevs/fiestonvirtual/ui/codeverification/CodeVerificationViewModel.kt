package com.spydevs.fiestonvirtual.ui.codeverification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeVerificationViewModel(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {
    private val _isSuccessCode = MutableLiveData<CodeVerificationResult>()

    val isSuccessCode: LiveData<CodeVerificationResult>
        get() = _isSuccessCode

    private val _error = MutableLiveData<CodeVerificationResult>()

    val error: LiveData<CodeVerificationResult>
        get() = _error

    private val _loading = MutableLiveData<CodeVerificationResult>()

    val loading: LiveData<CodeVerificationResult>
        get() = _loading

    fun verifyCode(code: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            _loading.value = CodeVerificationResult.Loading(true)
            if (!code.isNullOrEmpty()) {
                when (val loginResult =
                    loginUserUseCase.invoke(ValidateCodeRequest(code.toInt()))) {
                    is ResultType.Success -> {
                        _isSuccessCode.value = CodeVerificationResult.CodeVerificationSuccessful
                    }
                    is ResultType.Error -> {
                        _error.value =
                            CodeVerificationResult.CodeVerificationError(loginResult.value)
                    }
                }
            }
            _loading.value = CodeVerificationResult.Loading(false)
        }
    }
}