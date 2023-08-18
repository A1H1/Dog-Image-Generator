package com.dogimagegenerator.app.data.api

import com.dogimagegenerator.app.data.model.DogImageResponse
import retrofit2.http.GET

interface DogAPIService {
    @GET("breeds/image/random")
    suspend fun getDogImage(): DogImageResponse
}