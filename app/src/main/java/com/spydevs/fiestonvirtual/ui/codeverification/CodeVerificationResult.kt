package com.spydevs.fiestonvirtual.ui.codeverification

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse

sealed class CodeVerificationResult {
    class Loading(val show: Boolean) : CodeVerificationResult()
    object CodeVerificationSuccessful : CodeVerificationResult()
    class CodeVerificationError(val errorResponse: ErrorResponse) : CodeVerificationResult()
}