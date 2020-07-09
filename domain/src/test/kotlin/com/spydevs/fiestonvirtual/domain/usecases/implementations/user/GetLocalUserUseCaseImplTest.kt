package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetLocalUserUseCaseImplTest {

    @Mock
    lateinit var usersRepository: UsersRepository

    private lateinit var getLocalUserUseCaseImpl: GetLocalUserUseCaseImpl

    @Before
    fun setUp() {
        this.getLocalUserUseCaseImpl = GetLocalUserUseCaseImpl(usersRepository)
    }

    @Test
    fun `get local user success`() {
        runBlocking {
            Mockito.`when`(
                usersRepository.getLocalUser()
            ).thenReturn(
                User(
                    1,
                    "",
                    "",
                    0,
                    0,
                    0
                )
            )

            getLocalUserUseCaseImpl()

            Mockito.verify(
                usersRepository,
                VerificationModeFactory.times(1)
            ).getLocalUser()

        }
    }

}