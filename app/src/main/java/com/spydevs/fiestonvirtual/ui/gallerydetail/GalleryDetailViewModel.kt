package com.spydevs.fiestonvirtual.ui.gallerydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment.GetCommentsUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetPostDetailUseCase
import com.spydevs.fiestonvirtual.ui.main.gallery.GalleryResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryDetailViewModel(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val getPostDetailUseCase: GetPostDetailUseCase
) : ViewModel() {

    private val _commentList = MutableLiveData<List<Comment>>()
    val commentList: LiveData<List<Comment>>
        get() = _commentList

    private val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error

    private val _loading = MutableLiveData<GalleryResult.Loading>()
    val loading: LiveData<GalleryResult.Loading>
        get() = _loading

    private val _postDetail = MutableLiveData<GalleryResult.GetGallerySuccessful>()
    val postDetail: LiveData<GalleryResult.GetGallerySuccessful> = _postDetail

    fun getCommentList(idPost: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            when (val result = getCommentsUseCase(CommentRequest(idPost))) {
                is ResultType.Success -> {
                    _commentList.value = result.value
                }
                is ResultType.Error -> {
                    _error.value = result.value
                }
            }
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

}