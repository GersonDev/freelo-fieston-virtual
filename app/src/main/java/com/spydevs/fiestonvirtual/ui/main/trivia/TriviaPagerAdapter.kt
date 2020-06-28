package com.spydevs.fiestonvirtual.ui.main.trivia

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia

class TriviaPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private var triviaModelList = mutableListOf<Trivia>()

    override fun createFragment(position: Int): Fragment =
        TriviaPageFragment(triviaModelList[position])

    override fun getItemCount(): Int = triviaModelList.size

    fun addAllData(triviaModelList: List<Trivia>) {
        clearData()
        this.triviaModelList.addAll(triviaModelList)
        notifyDataSetChanged()
    }

    private fun clearData() {
        this.triviaModelList.clear()
    }

}