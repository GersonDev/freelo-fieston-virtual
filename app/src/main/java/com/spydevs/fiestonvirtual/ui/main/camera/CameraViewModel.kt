package com.spydevs.fiestonvirtual.ui.main.camera

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.photo.Photo
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.UploadImageUseCase
import com.spydevs.fiestonvirtual.util.formatter.ImageFormattingStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CameraViewModel(
    private val uploadImageUseCase: UploadImageUseCase,
    private val imageFormattingStrategy: ImageFormattingStrategy
) : ViewModel() {

    private val _uploadedImage = MutableLiveData<Photo>()
    val uploadedImage: LiveData<Photo>
        get() = _uploadedImage

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    //TODO FOR NOW WE ARE NOY GOING TO USE THIS METHOD, INSTEAD WE ARE USING WORKMANAGER AND WE ARE SHOWING NOTIFICATIONS
    fun uploadImage(imagePath: String) {
        viewModelScope.launch(Dispatchers.Main) {
            when (val result = uploadImageUseCase(imagePath)) {
                is ResultType.Success -> {
                    _uploadedImage.value = result.value
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
        }
    }

    private suspend fun convertImage(bitmap: Bitmap) = withContext(Dispatchers.IO) {
        imageFormattingStrategy.format(bitmap)
    }

}