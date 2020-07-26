package com.spydevs.fiestonvirtual.ui.main.trivia.answer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import kotlinx.android.synthetic.main.layout_item_trivia_answer.view.*

class AnswerViewHolder(
    private val view: View,
    private val onOptionClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private lateinit var answerModel: Trivia.Alternative

    init {
        this.view.itemTriviaAnswer_btn.setOnClickListener {
            onOptionClickListener(adapterPosition)
        }
    }

    fun bind(alternative: Trivia.Alternative) {
        this.answerModel = alternative
        this.view.itemTriviaAnswer_btn.apply {
            text = alternative.alternativeDescription
        }
    }

}