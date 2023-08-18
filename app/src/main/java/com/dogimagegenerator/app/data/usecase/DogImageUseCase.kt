package com.dogimagegenerator.app.data.usecase

import com.dogimagegenerator.app.data.event.DataType
import com.dogimagegenerator.app.data.event.DataWrapper
import com.dogimagegenerator.app.data.event.ResponseDataType
import com.dogimagegenerator.app.data.repository.DogImageRepository

interface DogImageUseCase {
    suspend fun getDogImage(): DataWrapper<String>
    fun getDogImages(): List<String>
}

class DogImageUseCaseImpl(private val repository: DogImageRepository) : DogImageUseCase {
    override suspend fun getDogImage(): DataWrapper<String> {
        val response = repository.getDogImage()
        return if (response.type == ResponseDataType.ERROR || response.data == null) {
            DataWrapper(error = response.error, type = DataType.ERROR)
        } else {
            DataWrapper(data = response.data.message, type = DataType.SUCCESS)
        }
    }

    override fun getDogImages() = repository.getDogImages()
}