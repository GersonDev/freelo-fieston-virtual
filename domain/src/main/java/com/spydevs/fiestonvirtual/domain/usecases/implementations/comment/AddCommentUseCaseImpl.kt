package com.spydevs.fiestonvirtual.domain.usecases.implementations.comment

import com.spydevs.fiestonvirtual.domain.models.comment.AddCommentRequest
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.repository.CommentRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment.AddCommentUseCase

class AddCommentUseCaseImpl(
    private val commentRepository: CommentRepository,
    private val usersRepository: UsersRepository
) : AddCommentUseCase {
    override suspend fun invoke(postId: Int, comment: String): ResultType<Comment, ErrorResponse> {
        val user = usersRepository.getLocalUser()
        val addCommentRequest = AddCommentRequest(postId, user.id, comment)
        return commentRepository.addRemoteComments(addCommentRequest)
    }
}