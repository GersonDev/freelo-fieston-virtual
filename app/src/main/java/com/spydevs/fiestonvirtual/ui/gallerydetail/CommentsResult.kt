package com.spydevs.fiestonvirtual.ui.gallerydetail

import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.gallery.GetGalleryDetailResponse

sealed class CommentsResult {

    sealed class GetComments : CommentsResult() {
        data class Success(val comments: List<Comment>) : GetComments()
        data class Loading(var loading: Boolean) : GetComments()
    }

    sealed class AddComment : CommentsResult() {
        data class Success(val comment: Comment) : AddComment()
        data class Loading(var loading: Boolean) : AddComment()
    }

    sealed class MakeLike : CommentsResult() {
        data class Success(
            var likes: Int
        ) : MakeLike()

        data class Loading(var loading: Boolean) : MakeLike()
    }

    sealed class GetGalleryDetail : CommentsResult() {
        data class Success(
            var galleryDetailResponse: GetGalleryDetailResponse
        ) : GetGalleryDetail()

        data class Loading(var loading: Boolean) : GetGalleryDetail()
    }

}