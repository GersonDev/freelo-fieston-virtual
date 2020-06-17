package com.spydevs.fiestonvirtual.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.Category
import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import kotlinx.android.synthetic.main.toolbar_main.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>().apply {
        value = mutableListOf(
            Category(
                "FOTOS",
                R.drawable.mango,
                "GALERIA DE FOTOS Y",
                "VIDEOS"
            ),
            Category(
                "CHAT",
                R.drawable.mango,
                "Participa de la fiesta",
                "PIDE UNA CANCION"
            ),
            Category(
                "PLAY LIST",
                R.drawable.mango,
                "CHATEAR AHORA",
                "ARMA LA CONVERSA"
            ),
            Category(
                "TRIVIAS",
                R.drawable.mango,
                "GANA PUNTOS CON LAS TRIVIAS AHORA",
                "JUGAR AHORA"
            )
        )
    }

    val categories: LiveData<List<Category>> = _categories

    private val _userMutableLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>>
        get() = _userMutableLiveData

    fun getUsers() {
        viewModelScope.launch(Dispatchers.Main) {
            _userMutableLiveData.value = usersRepository.getUser()
        }
    }

}