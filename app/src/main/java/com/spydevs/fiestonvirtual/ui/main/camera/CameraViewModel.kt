package com.spydevs.fiestonvirtual.ui.main.camera

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.UploadImageUseCase
import com.spydevs.fiestonvirtual.util.formatter.ImageFormattingStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CameraViewModel(
    private val uploadImageUseCase: UploadImageUseCase,
    private val imageFormattingStrategy: ImageFormattingStrategy
) : ViewModel() {

    private val _uploadedImage = MutableLiveData<String>()
    val uploadedImage: LiveData<String>
        get() = _uploadedImage

    fun uploadImage(bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.Main) {
            val formattedImage = convertImage(bitmap)
            val uploadedImageResponse = uploadImageUseCase(formattedImage)
            _uploadedImage.value = uploadedImageResponse
        }
    }

    private suspend fun convertImage(bitmap: Bitmap) = withContext(Dispatchers.IO) {
        imageFormattingStrategy.format(bitmap)
    }

}