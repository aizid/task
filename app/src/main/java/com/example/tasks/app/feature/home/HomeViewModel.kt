package com.example.tasks.app.feature.home

import androidx.lifecycle.viewModelScope
import com.example.tasks.app.base.viewmodel.BaseViewModel
import com.example.tasks.app.domain.dto.response.source.SourceRes
import com.example.tasks.app.domain.interactors.SourceByCategoryUseCase
import com.example.tasks.app.domain.subscribers.DataSource
import com.example.tasks.app.domain.subscribers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sourceByCategoryUseCase: SourceByCategoryUseCase,
): BaseViewModel() {

    /** Get Source List **/
    private val _getSource : MutableStateFlow<Resource<SourceRes>> =
        MutableStateFlow(Resource.Loading)
    val getSourceLiveData: StateFlow<Resource<SourceRes>> = _getSource
    fun getSourceList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_getSource.value != Resource.Loading) { _getSource.value = Resource.Loading }
            sourceByCategoryUseCase.invoke().collect{when (it) {
                is Resource.Success -> {
                    Timber.d("Success Product : ${it.data}")
                    _getSource.value = Resource.Success(data = it.data, DataSource.REMOTE)
                }
                is Resource.Failure -> {
                    _getSource.value = Resource.Failure(it.failureData)
                } else -> {}
            } }
        }
    }

}