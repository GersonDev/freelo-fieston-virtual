package com.spydevs.fiestonvirtual.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.spydevs.fiestonvirtual.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_user.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by inject()

    //TODO Remove Log for next tasks
    private val categoryAdapter by lazy {
        CategoryAdapter {
            when (it.name) {
                CATEGORY_NAME_PHOTO -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_gallery)
                }
                CATEGORY_NAME_CHAT -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_chat)
                }
                CATEGORY_NAME_TRIVIA -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_trivia)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToCategories()
        subscribeToUser()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupViewListeners()

        homeViewModel.getUsers()
    }

    private fun subscribeToCategories() {
        homeViewModel.categories.observe(viewLifecycleOwner, Observer {
            categoryAdapter.clearAllData()
            categoryAdapter.addData(it)
        })
    }

    private fun subscribeToUser() {
        homeViewModel.userLiveData.observe(viewLifecycleOwner, Observer { user ->
            nameTextView.text = user.name.plus(" ").plus(user.lastName)
        })
    }

    private fun setupViews() {
        (categoryRecyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = 2
        categoryRecyclerView.adapter = categoryAdapter
    }

    private fun setupViewListeners() {

    }

    companion object {
        const val CATEGORY_NAME_PHOTO = "FOTOS"
        const val CATEGORY_NAME_CHAT = "CHAT"
        const val CATEGORY_NAME_TRIVIA = "TRIVIAS"
        const val CATEGORY_NAME_PLAY_LIST = "PLAY LIST"
    }

}
