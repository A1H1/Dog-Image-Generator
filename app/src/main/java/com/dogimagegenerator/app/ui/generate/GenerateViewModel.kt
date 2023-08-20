package com.dogimagegenerator.app.ui.generate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogimagegenerator.app.data.event.DataWrapper
import com.dogimagegenerator.app.data.event.Event
import com.dogimagegenerator.app.data.usecase.DogImageUseCase
import com.dogimagegenerator.app.utils.postData
import kotlinx.coroutines.launch

class GenerateViewModel(private val useCase: DogImageUseCase) : ViewModel() {
    private val _image = MutableLiveData<Event<DataWrapper<String>>>()
    val image: LiveData<Event<DataWrapper<String>>> get() = _image

    fun getImage() {
        _image.postData(DataWrapper(isLoading = true))
        viewModelScope.launch { _image.postData(useCase.getDogImage()) }
    }
}