package com.spydevs.fiestonvirtual.ui.main.trivia.option

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.model.trivia.OptionModel

class OptionAdapter : RecyclerView.Adapter<OptionViewHolder>() {

    private lateinit var optionList: MutableList<OptionModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_trivia_option, parent, false)
        ) {
            setSelected(it)
        }
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(optionList[position])
    }

    fun addData(optionList: MutableList<OptionModel>) {
        this.optionList = optionList
    }

    private fun cleanSelected() {
        optionList.map { option ->
            option.checked = false
        }
    }

    private fun setSelected(position: Int) {
        if (!optionList[position].checked) {
            cleanSelected()
            optionList[position].checked = true
            notifyDataSetChanged()
        }
    }

}