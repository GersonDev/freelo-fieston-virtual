package com.spydevs.fiestonvirtual.ui.main.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.util.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.fragment_trivia.*
import org.koin.android.ext.android.inject

class TriviaFragment : Fragment(R.layout.fragment_trivia) {

    private val triviaViewModel: TriviaViewModel by inject()

    private val triviaPagerAdapter: TriviaPagerAdapter by lazy {
        TriviaPagerAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        subscribeToTrivia()
        triviaViewModel.getTrivia()
    }

    private fun setUpViews() {
        triviaFragment_vp.adapter = triviaPagerAdapter
        triviaFragment_vp.setPageTransformer(ZoomOutPageTransformer())
    }

    private fun subscribeToTrivia() {
        triviaViewModel.trivia.observe(viewLifecycleOwner, Observer {
            triviaPagerAdapter.addAllData(it)
        })
    }

}