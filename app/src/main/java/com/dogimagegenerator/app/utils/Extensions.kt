package com.dogimagegenerator.app.utils

import androidx.lifecycle.MutableLiveData
import com.dogimagegenerator.app.data.event.DataWrapper
import com.dogimagegenerator.app.data.event.Event

fun <T> MutableLiveData<Event<DataWrapper<T>>>.postData(dataWrapper: DataWrapper<T>) {
    postValue(Event(dataWrapper))
}

fun <T> MutableLiveData<Event<T>>.postEvent(data: T) {
    postValue(Event(data))
}