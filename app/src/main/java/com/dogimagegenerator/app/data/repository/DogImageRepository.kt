package com.dogimagegenerator.app.data.repository

import com.dogimagegenerator.app.base.ErrorHandler
import com.dogimagegenerator.app.data.api.DogAPIService
import com.dogimagegenerator.app.data.event.ResponseDataType
import com.dogimagegenerator.app.data.event.ResponseWrapper
import com.dogimagegenerator.app.data.local.PrefService
import com.dogimagegenerator.app.data.model.DogImageResponse

interface DogImageRepository {
    suspend fun getDogImage(): ResponseWrapper<DogImageResponse>
    fun getDogImages(): List<String>
}

class DogImageRepositoryImpl(
    private val prefService: PrefService,
    private val service: DogAPIService
) :
    DogImageRepository {
    override suspend fun getDogImage(): ResponseWrapper<DogImageResponse> {
        return try {
            val data = service.getDogImage()
            prefService.saveImage(data.message)
            ResponseWrapper(data = data, type = ResponseDataType.SUCCESS)
        } catch (e: Exception) {
            ResponseWrapper(error = ErrorHandler.parseError(e), type = ResponseDataType.ERROR)
        }
    }

    override fun getDogImages() = prefService.getImages()
}