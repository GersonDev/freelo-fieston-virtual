package com.spydevs.fiestonvirtual.ui.main.trivia.answer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.model.trivia.AnswerModel
import kotlinx.android.synthetic.main.layout_item_trivia_answer.view.*

class AnswerViewHolder(
    private val view: View,
    private val onOptionClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private lateinit var answerModel: AnswerModel

    init {
        this.view.itemTriviaAnswer_btn.setOnClickListener {
            onOptionClickListener(adapterPosition)
        }
    }

    fun bind(optionModel: AnswerModel) {
        this.answerModel = optionModel
        this.view.itemTriviaAnswer_btn.apply {
            text = optionModel.text
            if (optionModel.checked) {
                setBackgroundColor(
                    resources.getColor(
                        R.color.colorAccent,
                        null
                    )
                )
            } else {
                setBackgroundColor(
                    resources.getColor(
                        R.color.colorPrimary,
                        null
                    )
                )
            }
        }
    }

}