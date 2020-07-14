package com.spydevs.fiestonvirtual.ui.gallerydetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import kotlinx.android.synthetic.main.layout_item_comment.view.*

class CommentViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(comment: Comment) {
        itemView.apply {
            commentItem_iv_photo.loadUrl(comment.avatar)
            commentItem_tv_comment.text = comment.text
        }

    }

}