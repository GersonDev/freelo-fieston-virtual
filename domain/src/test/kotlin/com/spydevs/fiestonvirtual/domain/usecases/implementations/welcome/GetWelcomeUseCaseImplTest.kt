package com.spydevs.fiestonvirtual.domain.usecases.implementations.welcome

import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.models.welcome.WelcomeRequest
import com.spydevs.fiestonvirtual.domain.repository.EventRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory.times
import org.mockito.junit.MockitoJUnitRunner

/**
 * In order to read more information about testing with coroutines
 * @see [link] (https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/)
 */
@RunWith(MockitoJUnitRunner::class)
class GetWelcomeUseCaseImplTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var repository: EventRepository

    @Mock
    lateinit var usersRepository: UsersRepository

    lateinit var getWelcomeUseCaseImpl: GetWelcomeUseCaseImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        getWelcomeUseCaseImpl = GetWelcomeUseCaseImpl(repository, usersRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `given a signed in user then show a event welcome message`() {
        runBlocking {
            launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
                Mockito.`when`(usersRepository.getLocalUser()).thenReturn(fakeUser)
                getWelcomeUseCaseImpl()
                Mockito.verify(repository, times(1)).getWelcome(fakeWelcomeRequest)
            }
        }
    }

    private val fakeWelcomeRequest = WelcomeRequest(FAKE_ID_EVENT)
    private val fakeUser = User(
        1,
        "fakeName",
        "fakeLastName",
        8,
        8,
        FAKE_ID_EVENT
    )
    companion object {
        const val FAKE_ID_EVENT = 3
    }
}