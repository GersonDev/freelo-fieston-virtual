package com.spydevs.fiestonvirtual.ui.gallerydetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.comment.Comment

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {

    private val commentList: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_comment, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(this.commentList[position])
    }

    fun addData(commentList: List<Comment>) {
        this.commentList.addAll(commentList)
        notifyDataSetChanged()
    }

    fun addData(comment: Comment) {
        this.commentList.add(comment)
        notifyDataSetChanged()
    }

}