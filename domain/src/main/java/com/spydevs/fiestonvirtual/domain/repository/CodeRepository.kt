package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse

/**
 * This interface is used to verify the code of the event.
 */
interface CodeRepository {

    /**
     * This gets the validation and data of the user.
     */
    suspend fun verifyCode(code: String): CodeResponse

}