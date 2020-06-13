package com.spydevs.fiestonvirtual.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.Category
import kotlinx.android.synthetic.main.toolbar_main.view.*

class HomeViewModel : ViewModel() {

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

}