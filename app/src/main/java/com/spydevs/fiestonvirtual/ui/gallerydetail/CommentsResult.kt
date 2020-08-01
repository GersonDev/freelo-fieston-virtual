package com.spydevs.fiestonvirtual.ui.gallerydetail

import com.spydevs.fiestonvirtual.domain.models.comment.Comment

sealed class CommentsResult {

    sealed class GetComments: CommentsResult() {
        data class Success(val comments : List<Comment>) : GetComments()
        data class Loading(var loading : Boolean) : GetComments()
    }

    sealed class AddComment: CommentsResult() {
        data class Success(val comment : Comment) : AddComment()
        data class Loading(var loading : Boolean) : AddComment()
    }

}