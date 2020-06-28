package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.CommentDataSource
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.implementations.CommentListMapper

class CommentDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
) : CommentDataSource {

    override fun getRemoteComments(
        commentRequest: CommentRequest
    ): ResultType<List<Comment>, ErrorResponse> {

        return ResultType.Success(
            mutableListOf(
                Comment(
                    1,
                    "chevre",
                    "https://pngimg.com/uploads/face/face_PNG11760.png"
                ),
                Comment(
                    1,
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                    "https://pngimg.com/uploads/face/face_PNG11760.png"
                ),
                Comment(
                    1,
                    "exito",
                    "https://pngimg.com/uploads/face/face_PNG11760.png"
                ),
                Comment(
                    1,
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                    "https://pngimg.com/uploads/face/face_PNG11760.png"
                ),
                Comment(
                    1,
                    "eliminar",
                    "https://pngimg.com/uploads/face/face_PNG11760.png"
                ),
                Comment(
                    1,
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                    "https://pngimg.com/uploads/face/face_PNG11760.png"
                )
            )
        )
        /*
        return when (val result = fiestonVirtualApi.getComments(idPost)) {
            is NetworkResponse.Success -> {
                ResultType.Success(CommentListMapper.convertFromInitial(result.body.data.comments))
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
        }
         */
    }

}