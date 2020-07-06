package com.spydevs.fiestonvirtual.ui.main.trivia.answer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia

class AnswerAdapter : RecyclerView.Adapter<AnswerViewHolder>() {

    private var answerList = mutableListOf<Trivia.Alternative>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_trivia_answer, parent, false)
        ) {
            setSelected(it)
        }
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(answerList[position])
    }

    fun addData(alternativeList: List<Trivia.Alternative>) {
        this.answerList.addAll(alternativeList)
    }

    private fun cleanSelected() {
        answerList.forEach {
            it.isSelected = false
        }
    }

    private fun setSelected(position: Int) {
        if (!answerList[position].isSelected) {
            cleanSelected()
            answerList[position].isSelected = true
            notifyDataSetChanged()
        }
    }

}