package com.spydevs.fiestonvirtual.ui.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val getGalleryUseCase: GetGalleryUseCase
) : ViewModel() {

    private val _galleryItemList = MutableLiveData<List<GalleryItem>>()
    val galleryItemList: LiveData<List<GalleryItem>> = _galleryItemList

    private val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse> = _error

    fun getPhotoList(galleryRequest: GalleryRequest) {
        viewModelScope.launch(Dispatchers.Main) {
            when (val result = getGalleryUseCase(galleryRequest)) {
                is ResultType.Success -> {
                    _galleryItemList.value = result.value
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
        }
    }

}