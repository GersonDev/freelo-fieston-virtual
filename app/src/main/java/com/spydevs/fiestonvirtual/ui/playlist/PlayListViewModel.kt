package com.spydevs.fiestonvirtual.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist.GetPlayListUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist.RequestSongUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val requestSongUseCase: RequestSongUseCase,
    private val getPlayListUseCase: GetPlayListUseCase
) : ViewModel() {

    private val _playlist = MutableLiveData<PlaylistResult>()
    val playlist: LiveData<PlaylistResult>
        get() = _playlist

    fun requestSong(idPlaylist: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _playlist.value = PlaylistResult.RequestSong.Loading(true)
            when (val result = requestSongUseCase(idPlaylist)) {
                is ResultType.Success -> {
                    _playlist.value = PlaylistResult.RequestSong.Success(result.value)
                }
                is ResultType.Error -> {
                    _playlist.value = PlaylistResult.RequestSong.Error(result.value.message)
                }
            }
            _playlist.value = PlaylistResult.RequestSong.Loading(false)
        }
    }

    fun getPlaylist() {
        viewModelScope.launch(Dispatchers.Main) {
            _playlist.value = PlaylistResult.GetPlaylist.Loading(true)
            when (val result = getPlayListUseCase()) {
                is ResultType.Success -> {
                    _playlist.value = PlaylistResult.GetPlaylist.Success(result.value)
                }
                is ResultType.Error -> {
                    _playlist.value = PlaylistResult.GetPlaylist.Error(result.value.message)
                }
            }
            _playlist.value = PlaylistResult.GetPlaylist.Loading(false)
        }
    }

}