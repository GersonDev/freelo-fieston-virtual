package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.AddCommentResponseEntity
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object AddCommentMapper : Mapper<AddCommentResponseEntity, Comment>() {
    override fun convertFromInitial(i: AddCommentResponseEntity): Comment {
        return Comment(
            id = i.data.comment.idComment,
            text = i.data.comment.comment,
            avatar = i.data.comment.avatar,
            userName = i.data.comment.userComment
        )
    }
}