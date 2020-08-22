package com.spydevs.fiestonvirtual.ui.main

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse

sealed class MainResult {

    sealed class SignOut : MainResult() {
        object Success : SignOut()
        class Error(val errorResponse: ErrorResponse) : SignOut()
        class Loading(val show: Boolean) : SignOut()
    }

}