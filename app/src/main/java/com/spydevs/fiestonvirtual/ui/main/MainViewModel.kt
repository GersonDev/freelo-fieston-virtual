package com.spydevs.fiestonvirtual.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.SignOutUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.welcome.GetWelcomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getWelcomeUseCase: GetWelcomeUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _welcomeMutableLiveData = MutableLiveData<Welcome>()
    val welcome: LiveData<Welcome>
        get() = _welcomeMutableLiveData

    private val _signOut = MutableLiveData<MainResult.SignOut>()
    val signOut: LiveData<MainResult.SignOut>
        get() = _signOut

    fun getWelcome() {
        viewModelScope.launch(Dispatchers.Main) {
            when (val welcomeResultType = getWelcomeUseCase()) {
                is ResultType.Success -> {
                    _welcomeMutableLiveData.value = welcomeResultType.value
                }
                is ResultType.Error -> {
                    _error.value = welcomeResultType.value
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.Main) {
            _signOut.value = MainResult.SignOut.Loading(true)
            when (val result = signOutUseCase()) {
                is ResultType.Success -> {
                    _signOut.value = MainResult.SignOut.Success
                }
                is ResultType.Error -> {
                    _signOut.value = MainResult.SignOut.Error(result.value)
                }
            }
            _signOut.value = MainResult.SignOut.Loading(false)
        }
    }
}