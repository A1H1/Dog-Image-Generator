package com.dogimagegenerator.app.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogimagegenerator.app.data.event.Event
import com.dogimagegenerator.app.data.usecase.DogImageUseCase
import com.dogimagegenerator.app.utils.postEvent
import kotlinx.coroutines.launch

class HistoryViewModel(private val useCase: DogImageUseCase) : ViewModel() {
    private val _images = MutableLiveData<Event<List<String>>>()
    val images: LiveData<Event<List<String>>> get() = _images

    fun getImages() {
        viewModelScope.launch { _images.postEvent(useCase.getDogImages()) }
    }

    fun clearDogs() {
        viewModelScope.launch {
            useCase.clearImages()
            _images.postEvent(emptyList())
        }
    }
}
