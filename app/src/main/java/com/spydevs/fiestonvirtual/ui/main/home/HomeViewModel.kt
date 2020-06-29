package com.spydevs.fiestonvirtual.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.Category
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.GetLocalUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserUseCase: GetLocalUserUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>().apply {
        value = mutableListOf(
            Category(
                HomeFragment.CATEGORY_NAME_PHOTO,
                R.drawable.ic_home_fotos,
                "GALERIA DE FOTOS Y",
                "VIDEOS"
            ),
            Category(
                HomeFragment.CATEGORY_NAME_CHAT,
                R.drawable.ic_home_chat,
                "CHATEAR AHORA",
                "ARMA LA CONVERSA"
            ),
            Category(
                HomeFragment.CATEGORY_NAME_PLAY_LIST,
                R.drawable.ic_home_playlist,
                "Participa de la fiesta",
                "PIDE UNA CANCION"
            ),
            Category(
                HomeFragment.CATEGORY_NAME_TRIVIA,
                R.drawable.ic_home_trivia,
                "GANA PUNTOS CON LAS TRIVIAS AHORA",
                "JUGAR AHORA"
            )
        )
    }

    val categories: LiveData<List<Category>> = _categories

    private val _userMutableLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() = _userMutableLiveData

    fun getUsers() {
        viewModelScope.launch(Dispatchers.Main) {
            //_userMutableLiveData.value = getUserUseCase()
        }
    }

}