package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUserUseCaseImplTest {

    lateinit var loginUserUseCaseImpl: LoginUserUseCaseImpl

    @Mock
    lateinit var codeRepository: CodeRepository

    @Mock
    lateinit var usersRepository: UsersRepository

    @Before
    fun setUp() {
        loginUserUseCaseImpl = LoginUserUseCaseImpl(
            codeRepository,
            usersRepository
        )
    }

    @Test
    fun `Verification login user when success`() {
        val fakeValidateCodeRequest = ValidateCodeRequest(1)
        val fakeResultEventCode: ResultType<EventCode, ErrorResponse> =
            ResultType.Success(EventCode(1, 1))
        val fakeGetRemoteUserRequest =
            GetRemoteUserRequest((fakeResultEventCode as ResultType.Success).value.idUser)
        val fakeResultUser: ResultType<User, ErrorResponse> =
            ResultType.Success(
                User(
                    1,
                    "",
                    "",
                    0,
                    0,
                    0
                )
            )

        runBlocking {
            Mockito.`when`(
                codeRepository.verifyCode(fakeValidateCodeRequest)
            ).thenReturn(fakeResultEventCode)

            Mockito.`when`(
                usersRepository.getRemoteUser(fakeGetRemoteUserRequest)
            ).thenReturn(fakeResultUser)

            loginUserUseCaseImpl(fakeValidateCodeRequest)

            Mockito.verify(
                codeRepository,
                VerificationModeFactory.times(1)
            ).verifyCode(fakeValidateCodeRequest)

            Mockito.verify(
                usersRepository,
                VerificationModeFactory.times(1)
            ).getRemoteUser(fakeGetRemoteUserRequest)

            Mockito.verify(
                usersRepository,
                VerificationModeFactory.times(1)
            ).setLoggedInUser((fakeResultUser as ResultType.Success).value)
        }
    }

    @Test
    fun `verification login when verify code failure`() {
        val fakeValidateCodeRequest = ValidateCodeRequest(1)
        val fakeResultEventCode: ResultType<EventCode, ErrorResponse> =
            ResultType.Error(ErrorResponse())

        runBlocking {
            Mockito.`when`(
                codeRepository.verifyCode(fakeValidateCodeRequest)
            ).thenReturn(fakeResultEventCode)

            Assert.assertEquals(
                loginUserUseCaseImpl(fakeValidateCodeRequest),
                fakeResultEventCode
            )
        }
    }

}