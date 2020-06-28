package com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment

import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case gets comments to post.
 */
interface GetCommentsUseCase {

    /**
     * @param [commentRequest] This object is necessary for return the comments.
     * @return all the comments of the post.
     */
    suspend operator fun invoke(
        commentRequest: CommentRequest
    ): ResultType<List<Comment>, ErrorResponse>

}