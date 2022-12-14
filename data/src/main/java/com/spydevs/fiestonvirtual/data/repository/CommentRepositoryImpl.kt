package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.CommentDataSource
import com.spydevs.fiestonvirtual.domain.models.comment.AddCommentRequest
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.repository.CommentRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class CommentRepositoryImpl(
    private val commentDataSource: CommentDataSource
) : CommentRepository {

    override suspend fun getRemoteComments(
        commentRequest: CommentRequest
    ): ResultType<List<Comment>, ErrorResponse> {
        return commentDataSource.getRemoteComments(commentRequest)
    }

    override suspend fun addRemoteComments(
        addCommentRequest: AddCommentRequest
    ): ResultType<Comment, ErrorResponse> {
        return commentDataSource.addRemoteComment(addCommentRequest)
    }

}