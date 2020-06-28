package com.spydevs.fiestonvirtual.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.Category
import kotlinx.android.synthetic.main.item_category_normal.view.*

class CategoryAdapter(
    private val categories: MutableList<Category> = mutableListOf(),
    private val onProductClickListener: (category: Category) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val categoryNormalView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_category_normal, parent, false)
        return CategoryAllViewHolder(categoryNormalView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryAllViewHolder).bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryAllViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var categoryModel: Category

        init {
            itemView.setOnClickListener {
                onProductClickListener(categoryModel)
            }
        }

        fun bind(category: Category) {
            this.categoryModel = category
            itemView.categoryForegroundImageView.setImageResource(category.image)
            itemView.categoryNameTextView.text = category.name
            itemView.descriptionTextView.text = category.description
            itemView.moreDescriptionTextView.text = category.subDescription
        }
    }

    fun addData(productsModels: List<Category>) {
        categories.addAll(productsModels)
        notifyDataSetChanged()
    }

    fun clearAllData() {
        categories.clear()
        notifyDataSetChanged()
    }

}