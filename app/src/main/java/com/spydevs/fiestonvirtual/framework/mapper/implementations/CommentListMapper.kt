package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.GetCommentsResponseEntity
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object CommentListMapper : Mapper<List<GetCommentsResponseEntity.Comment>, List<Comment>>() {

    override fun convertFromInitial(i: List<GetCommentsResponseEntity.Comment>): List<Comment> {
        return i.map {
            Comment(
                id = it.idComment,
                text = it.comment,
                avatar = it.avatar
            )
        }
    }

}