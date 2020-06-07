package com.spydevs.fiestonvirtual.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spydevs.fiestonvirtual.domain.models.Category

class HomeViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>().apply {
        value = mutableListOf(
            Category(
                "category2",
                "https://chefsmandala.com/wp-content/uploads/2018/03/Mango-200x200.jpg"
            ),
            Category(
                "category2",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSwdjWanvnOBGKzEkHzrxp5Yd2hmW3wKeNZjBYSc8u_MP7kK1Bj&usqp=CAU"
            ),
            Category(
                "category2",
                "https://chefsmandala.com/wp-content/uploads/2018/03/Mango-200x200.jpg"
            ),
            Category(
                "category2",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSwdjWanvnOBGKzEkHzrxp5Yd2hmW3wKeNZjBYSc8u_MP7kK1Bj&usqp=CAU"
            ),
            Category(
                "category2",
                "https://chefsmandala.com/wp-content/uploads/2018/03/Mango-200x200.jpg"
            )
        )
    }

    val categories: LiveData<List<Category>> = _categories

}