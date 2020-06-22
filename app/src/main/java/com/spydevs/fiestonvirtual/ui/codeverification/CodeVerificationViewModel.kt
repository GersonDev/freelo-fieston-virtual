package com.spydevs.fiestonvirtual.ui.codeverification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.SetLoggedInUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeVerificationViewModel(
    private val verifyEventCodeUseCase: VerifyEventCodeUseCase,
    private val setLoggedInUserUseCase: SetLoggedInUserUseCase
) : ViewModel() {
    private val _isSuccessCode = MutableLiveData<Boolean>()

    val isSuccessCode: LiveData<Boolean>
        get() = _isSuccessCode

    //TODO refactor when service is correct, this method must be tested.
    fun verifyCode(code: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            if (!code.isNullOrEmpty()) {
                val codeResponse = verifyEventCodeUseCase.invoke(code)
                if (codeResponse[1].mensaje == "success") {
                    codeResponse[0].let { codeResponseItem ->
                        setLoggedInUserUseCase.invoke(User().apply {
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