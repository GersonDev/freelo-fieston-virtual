package com.spydevs.fiestonvirtual.ui.gallerydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment.AddCommentUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment.GetCommentsUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetPostDetailUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.like.MakeLikeUseCase
import com.spydevs.fiestonvirtual.ui.main.gallery.GalleryResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryDetailViewModel(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val getPostDetailUseCase: GetPostDetailUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val makeLikeUseCase: MakeLikeUseCase
) : ViewModel() {

    private val _commentResult = MutableLiveData<CommentsResult>()
    val commentResult: LiveData<CommentsResult>
        get() = _commentResult

    private val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error

    private val _loading = MutableLiveData<GalleryResult.Loading>()
    val loading: LiveData<GalleryResult.Loading>
        get() = _loading

    private val _postDetail = MutableLiveData<GalleryResult.GetGallerySuccessful>()
    val postDetail: LiveData<GalleryResult.GetGallerySuccessful> = _postDetail

    private val _makeLike = MutableLiveData<CommentsResult.MakeLike>()
    val makeLike: LiveData<CommentsResult.MakeLike> = _makeLike

    fun getComments(idPost: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _commentResult.value = CommentsResult.GetComments.Loading(true)
            when (val result = getCommentsUseCase(CommentRequest(idPost))) {
                is ResultType.Success -> {
                    _commentResult.value = CommentsResult.GetComments.Success(result.value)
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _commentResult.value = CommentsResult.GetComments.Loading(false)
        }
    }

    fun getPostDetail(postId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _loading.value = GalleryResult.Loading(true)
            when (val result = getPostDetailUseCase(postId)) {
                is ResultType.Success -> {
                    _postDetail.value = GalleryResult.GetGallerySuccessful(result.value)
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _loading.value = GalleryResult.Loading(false)
        }
    }

    fun addComment(postId: Int, comment: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _commentResult.value = CommentsResult.AddComment.Loading(true)
            when (val result = addCommentUseCase(postId, comment)) {
                is ResultType.Success -> {
                    _commentResult.value = CommentsResult.AddComment.Success(result.value)
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _commentResult.value = CommentsResult.AddComment.Loading(false)
        }
    }

    fun makeLike(postId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _makeLike.value = CommentsResult.MakeLike.Loading(true)
            when (val result = makeLikeUseCase(postId)) {
                is ResultType.Success -> {
                    _makeLike.value = CommentsResult.MakeLike.Success(
                        result.value.likes
                    )
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _makeLike.value = CommentsResult.MakeLike.Loading(false)
        }
    }

}