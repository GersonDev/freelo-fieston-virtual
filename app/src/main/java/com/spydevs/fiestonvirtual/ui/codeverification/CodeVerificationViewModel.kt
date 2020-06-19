package com.spydevs.fiestonvirtual.ui.codeverification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.usecases.CodeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeVerificationViewModel(private val codeUseCase: CodeUseCase) : ViewModel() {
    private val _isSuccessCode = MutableLiveData<Boolean>()

    val isSuccessCode: LiveData<Boolean>
        get() = _isSuccessCode

    //TODO refactor in the next PR...
    fun verifyCode(code: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            if (!code.isNullOrEmpty()) {
                if (codeUseCase.verifyCode(code)[1].mensaje == "success") {
                    _isSuccessCode.value = true
                }
            }
        }
    }
}