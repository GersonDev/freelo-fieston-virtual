package com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment

import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case gets comments to post.
 */
interface AddCommentUseCase {

    /**
     * @param [addCommentRequest] Request object to create a new comment on server side.
     * @return all the comments of the post.
     */
    suspend operator fun invoke(
        postId: Int,
        comment: String
    ): ResultType<Comment, ErrorResponse>

}