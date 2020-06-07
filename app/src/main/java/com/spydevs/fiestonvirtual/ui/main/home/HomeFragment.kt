package com.spydevs.fiestonvirtual.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupViewListeners()
    }

    private fun subscribeToCategories() {
        homeViewModel.categories.observe(viewLifecycleOwner, Observer {
//            categoryAdapter.clearAllData()
//            categoryAdapter.addData(it)
        })
    }

    private fun setupViews() {
        (categoryRecyclerView.layoutManager as GridLayoutManager).spanCount = 3
        categoryRecyclerView.adapter = categoryAdapter

    }

    private fun setupViewListeners() {

    }

    private fun refreshCarousel() {

    }
}
