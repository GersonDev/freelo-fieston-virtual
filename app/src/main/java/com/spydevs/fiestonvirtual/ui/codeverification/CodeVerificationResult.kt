package com.spydevs.fiestonvirtual.ui.codeverification

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse

sealed class CodeVerificationResult {
    class Loading(val show: Boolean) : CodeVerificationResult()
    object CodeVerificationSuccessful : CodeVerificationResult()
    class CodeVerificationError(val errorResponse: ErrorResponse) : CodeVerificationResult()

    sealed class VerificationSession : CodeVerificationResult() {
        class Success(val inSession: Boolean) : VerificationSession()
        class Loading(val show: Boolean) : VerificationSession()
        class Error(val errorResponse: ErrorResponse) : VerificationSession()
    }

}