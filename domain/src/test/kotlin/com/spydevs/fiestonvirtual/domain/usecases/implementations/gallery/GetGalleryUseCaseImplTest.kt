package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase
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
class GetGalleryUseCaseImplTest {

    @Mock
    lateinit var mockGalleryRepository: GalleryRepository

    @Mock
    lateinit var mockUsersRepository: UsersRepository

    lateinit var getGalleryUseCaseImpl: GetGalleryUseCase

    @Before
    fun setUp() {
        this.getGalleryUseCaseImpl = GetGalleryUseCaseImpl(
            mockGalleryRepository,
            mockUsersRepository
        )
    }

    @Test
    fun `verify get gallery success`() {
        val user = User(
            2,
            "Jorge",
            "Flores",
            1,
            1,
            2
        )

        runBlocking {
            Mockito.`when`(
                mockUsersRepository.getLocalUser()
            ).thenReturn(
                user
            )

            getGalleryUseCaseImpl()

            Mockito.verify(
                mockGalleryRepository,
                VerificationModeFactory.times(1)
            ).getGallery(GalleryRequest(user.id, user.idEvent, 1))
        }
    }

    @Test
    fun `verify get gallery error`() {
        val user = User(
            2,
            "Jorge",
            "Flores",
            1,
            1,
            2
        )

        val galleryRequest = GalleryRequest(user.id, user.idEvent, 1)

        val resultError: ResultType<List<GalleryItem>, ErrorResponse> =
            ResultType.Error(
                ErrorResponse()
            )

        runBlocking {
            Mockito.`when`(
                mockUsersRepository.getLocalUser()
            ).thenReturn(
                user
            )
            Mockito.`when`(
                mockGalleryRepository.getGallery(galleryRequest)
            ).thenReturn(
                resultError
            )

            Assert.assertEquals(getGalleryUseCaseImpl(), resultError)

        }
    }

}