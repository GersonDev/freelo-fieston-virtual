package com.spydevs.fiestonvirtual.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spydevs.fiestonvirtual.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by inject()

    //TODO Remove Log for next tasks
    private val categoryAdapter by lazy {
        CategoryAdapter {
            when (it.name) {
                "FOTOS" -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_gallery)
                }
                "CHAT" -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_chat)
                }
                "TRIVIAS" -> {
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
        homeViewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            //TODO populate the user ui
        })
    }

    private fun setupViews() {
        (categoryRecyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = 2
        categoryRecyclerView.adapter = categoryAdapter
    }

    private fun setupViewListeners() {

    }

}
