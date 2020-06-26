package com.spydevs.fiestonvirtual.ui.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spydevs.fiestonvirtual.domain.models.photo.Photo

class GalleryViewModel : ViewModel() {

    private val _photoList = MutableLiveData<List<Photo>>()
    val photoList: LiveData<List<Photo>> = _photoList

    fun getPhotoList() {
        _photoList.value = mutableListOf(
            Photo(urlPhoto = "https://pngimg.com/uploads/face/face_PNG11760.png"),
            Photo(urlPhoto = "http://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "http://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "http://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612"),
            Photo(urlPhoto = "https://media.gettyimages.com/photos/season-2-pictured-meghan-markle-photo-by-paul-drinkwaternbcnbcu-photo-picture-id883186286?s=612x612")
        )
    }

}