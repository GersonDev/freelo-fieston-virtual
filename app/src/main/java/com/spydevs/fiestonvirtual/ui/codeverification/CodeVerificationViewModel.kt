package com.spydevs.fiestonvirtual.ui.codeverification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.domain.usecases.CodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeVerificationViewModel(
    private val codeUseCase: CodeUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _isSuccessCode = MutableLiveData<Boolean>()

    val isSuccessCode: LiveData<Boolean>
        get() = _isSuccessCode

    //TODO refactor
    fun verifyCode(code: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            if (!code.isNullOrEmpty()) {
                val codeResponse = codeUseCase.verifyCode(code)
                if (codeResponse[1].mensaje == "success") {
                    codeResponse[0].let { codeResponseItem ->
                        userUseCase.insertUser(User().apply {
                            name = codeResponseItem.nomUs
                            lastName = codeResponseItem.apePat
                        })
                    }
                    _isSuccessCode.value = true
                }
            }
        }
    }
}