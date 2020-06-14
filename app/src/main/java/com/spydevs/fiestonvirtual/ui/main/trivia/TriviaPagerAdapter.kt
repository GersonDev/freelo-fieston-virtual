package com.spydevs.fiestonvirtual.ui.main.trivia

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.spydevs.fiestonvirtual.model.trivia.TriviaModel

class TriviaPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private var triviaModelList = emptyList<TriviaModel>()

    override fun createFragment(position: Int): Fragment =
        TriviaPageFragment(triviaModelList[position])

    override fun getItemCount(): Int = triviaModelList.size

    fun addData(triviaModelList: List<TriviaModel>) {
        this.triviaModelList = triviaModelList
    }

}