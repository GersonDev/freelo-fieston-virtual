package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.comment.AddCommentRequest
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This repository manager the comments.
 */
interface CommentRepository {

    /**
     * @param [commentRequest] This object is necessary for return the comments.
     * @return all the comments of the post from the web services.
     */
    suspend fun getRemoteComments(
        commentRequest: CommentRequest
    ): ResultType<List<Comment>, ErrorResponse>

    /**
     * @param [addCommentRequest] Necessary for creating a new comment on the server side.
     */
    suspend fun addRemoteComments(
        addCommentRequest: AddCommentRequest
    ): ResultType<Comment, ErrorResponse>

}