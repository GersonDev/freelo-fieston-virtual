package com.spydevs.fiestonvirtual.ui.main.gallery

import android.os.Build
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class GalleryViewModelTest {

    private lateinit var galleryViewModel: GalleryViewModel

    @Mock
    lateinit var mockGetGalleryUseCase: GetGalleryUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        galleryViewModel = GalleryViewModel(mockGetGalleryUseCase)
    }

    @Test
    fun `validate get gallery success`() {
        runBlocking {
            val list = mutableListOf(
                GalleryItem(1, 1, "", 1)
            )
            val resultSuccess: ResultType<List<GalleryItem>, ErrorResponse> =
                ResultType.Success(list)
            val getGallerySuccessful = GalleryResult.GetGallerySuccessful(
                (resultSuccess as ResultType.Success).value
            )
            Mockito.`when`(
                mockGetGalleryUseCase()
            ).thenReturn(resultSuccess)

            galleryViewModel.getPhotoList()

            Assert.assertEquals(galleryViewModel.galleryItemList.value, getGallerySuccessful)
        }
    }

    @Test
    fun `verify get gallery failure`() {
        runBlocking {
            val errorResponse = ErrorResponse(
                0, "error", "message"
            )

            val resultError: ResultType<List<GalleryItem>, ErrorResponse> =
                ResultType.Error(errorResponse)

            val getGalleryError = GalleryResult.GetGalleryError(
                errorResponse
            )
            Mockito.`when`(
                mockGetGalleryUseCase()
            ).thenReturn(resultError)

            galleryViewModel.getPhotoList()

            Assert.assertEquals(galleryViewModel.error.value, getGalleryError)
        }
    }

}