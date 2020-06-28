package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface CommentDataSource {

    /**
     * @param [commentRequest] This object is necessary for return the comments.
     * @return all the comments of the post from the web services.
     */
    fun getRemoteComments(
        commentRequest: CommentRequest
    ): ResultType<List<Comment>, ErrorResponse>

}