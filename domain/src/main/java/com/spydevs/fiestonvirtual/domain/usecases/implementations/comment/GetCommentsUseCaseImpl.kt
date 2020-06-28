package com.spydevs.fiestonvirtual.domain.usecases.implementations.comment

import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.repository.CommentRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment.GetCommentsUseCase

class GetCommentsUseCaseImpl(
    private val commentRepository: CommentRepository
) : GetCommentsUseCase {

    override suspend operator fun invoke(
        commentRequest: CommentRequest
    ): ResultType<List<Comment>, ErrorResponse> {
        return commentRepository.getRemoteComments(commentRequest)
    }

}