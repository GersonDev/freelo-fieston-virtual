package com.spydevs.fiestonvirtual.domain.usecases.implementations.welcome

import com.spydevs.fiestonvirtual.domain.repository.EventRepository
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

    lateinit var getWelcomeUseCaseImpl: GetWelcomeUseCaseImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        getWelcomeUseCaseImpl = GetWelcomeUseCaseImpl(repository)
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
                getWelcomeUseCaseImpl.invoke()
                Mockito.verify(repository, times(1)).getWelcome(1,1)
            }
        }
    }
}