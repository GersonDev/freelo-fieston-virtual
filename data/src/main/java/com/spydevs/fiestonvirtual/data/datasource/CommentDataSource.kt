package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.comment.AddCommentRequest
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface CommentDataSource {

    /**
     * @param [commentRequest] This object is necessary for return the comments.
     * @return all the comments of the post from the web services.
     */
    suspend fun getRemoteComments(
        commentRequest: CommentRequest
    ): ResultType<List<Comment>, ErrorResponse>

    /**
     * @param [addCommentRequest] Request object fore creating a new comment on server side.
     */
    suspend fun addRemoteComment(
        addCommentRequest: AddCommentRequest
    ): ResultType<Comment, ErrorResponse>
}