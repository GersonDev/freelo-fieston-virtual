package com.spydevs.fiestonvirtual.ui.main.trivia.answer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.model.trivia.AnswerModel

class AnswerAdapter : RecyclerView.Adapter<AnswerViewHolder>() {

    private lateinit var answerList: MutableList<AnswerModel>

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

    fun addData(optionList: MutableList<AnswerModel>) {
        this.answerList = optionList
    }

    private fun cleanSelected() {
        answerList.map { option ->
            option.checked = false
        }
    }

    private fun setSelected(position: Int) {
        if (!answerList[position].checked) {
            cleanSelected()
            answerList[position].checked = true
            notifyDataSetChanged()
        }
    }

}