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

    private val _commentResult = MutableLiveData<GalleryDetailResult>()
    val commentResult: LiveData<GalleryDetailResult>
        get() = _commentResult

    private val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error

    private val _loading = MutableLiveData<GalleryResult.Loading>()
    val loading: LiveData<GalleryResult.Loading>
        get() = _loading

    private val _postDetail = MutableLiveData<GalleryDetailResult.GetGalleryDetail>()
    val postDetail: LiveData<GalleryDetailResult.GetGalleryDetail> = _postDetail

    private val _makeLike = MutableLiveData<GalleryDetailResult.MakeLike>()
    val makeLike: LiveData<GalleryDetailResult.MakeLike> = _makeLike

    fun getComments(idPost: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _commentResult.value = GalleryDetailResult.GetComments.Loading(true)
            when (val result = getCommentsUseCase(CommentRequest(idPost))) {
                is ResultType.Success -> {
                    _commentResult.value = GalleryDetailResult.GetComments.Success(result.value)
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _commentResult.value = GalleryDetailResult.GetComments.Loading(false)
        }
    }

    fun getPostDetail(postId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _postDetail.value = GalleryDetailResult.GetGalleryDetail.Loading(true)
            when (val result = getPostDetailUseCase(postId)) {
                is ResultType.Success -> {
                    _postDetail.value = GalleryDetailResult.GetGalleryDetail.Success(result.value)
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _postDetail.value = GalleryDetailResult.GetGalleryDetail.Loading(false)
        }
    }

    fun addComment(postId: Int, comment: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _commentResult.value = GalleryDetailResult.AddComment.Loading(true)
            when (val result = addCommentUseCase(postId, comment)) {
                is ResultType.Success -> {
                    _commentResult.value = GalleryDetailResult.AddComment.Success(result.value)
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _commentResult.value = GalleryDetailResult.AddComment.Loading(false)
        }
    }

    fun makeLike(postId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _makeLike.value = GalleryDetailResult.MakeLike.Loading(true)
            when (val result = makeLikeUseCase(postId)) {
                is ResultType.Success -> {
                    _makeLike.value = GalleryDetailResult.MakeLike.Success(
                        result.value.likes
                    )
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
            _makeLike.value = GalleryDetailResult.MakeLike.Loading(false)
        }
    }

}