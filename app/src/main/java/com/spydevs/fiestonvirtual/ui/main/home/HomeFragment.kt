package com.spydevs.fiestonvirtual.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.spydevs.fiestonvirtual.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by inject()

    //TODO Remove Log for next tasks
    private val categoryAdapter by lazy {
        CategoryAdapter {
            Log.e("CATEGORY", "CATEGORY")
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
