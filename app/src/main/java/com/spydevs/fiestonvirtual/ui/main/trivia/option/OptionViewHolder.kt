package com.spydevs.fiestonvirtual.ui.main.trivia.option

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.model.trivia.OptionModel
import kotlinx.android.synthetic.main.layout_item_trivia_option.view.*

class OptionViewHolder(
    private val view: View,
    private val onOptionClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private lateinit var optionModel: OptionModel

    init {
        this.view.itemTriviaOption_btn.setOnClickListener {
            onOptionClickListener(adapterPosition)
        }
    }

    fun bind(optionModel: OptionModel) {
        this.optionModel = optionModel
        this.view.itemTriviaOption_btn.apply {
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