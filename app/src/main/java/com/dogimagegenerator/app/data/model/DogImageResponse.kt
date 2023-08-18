package com.dogimagegenerator.app.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class DogImageResponse(
    @field:Json(name = "message")
    val message: String,
    @field:Json(name = "status")
    val status: String
)