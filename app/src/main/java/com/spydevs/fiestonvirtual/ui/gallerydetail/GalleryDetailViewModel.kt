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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryDetailViewModel(
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    private val _commentList = MutableLiveData<List<Comment>>()
    val commentList: LiveData<List<Comment>>
        get() = _commentList

    private val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error

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

}