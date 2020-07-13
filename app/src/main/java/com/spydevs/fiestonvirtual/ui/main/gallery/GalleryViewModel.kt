package com.spydevs.fiestonvirtual.ui.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val getGalleryUseCase: GetGalleryUseCase
) : ViewModel() {

    private val _galleryItemList = MutableLiveData<GalleryResult>()
    val galleryItemList: LiveData<GalleryResult> = _galleryItemList

    private val _error = MutableLiveData<GalleryResult>()
    val error: LiveData<GalleryResult> = _error

    private val _loading = MutableLiveData<GalleryResult>()
    val loading: LiveData<GalleryResult>
        get() = _loading

    fun getPhotoList() {
        viewModelScope.launch(Dispatchers.Main) {
            _loading.value = GalleryResult.Loading(true)
            when (val result = getGalleryUseCase()) {
                is ResultType.Success -> {
                    _galleryItemList.value = GalleryResult.GetGallerySuccessful(result.value)
                }
                is ResultType.Error -> {
                    _error.value = GalleryResult.GetGalleryError(result.value)
                }
            }
            _loading.value = GalleryResult.Loading(false)
        }
    }

}