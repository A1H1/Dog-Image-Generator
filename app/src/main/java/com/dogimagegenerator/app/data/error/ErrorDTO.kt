package com.dogimagegenerator.app.data.error

import com.dogimagegenerator.app.R

data class ErrorDTO(
    val title: String,
    val subTitle: String = "",
    val primaryButtonText: String = "",
    val secondaryButtonText: String = "",
    val icon: Int = R.drawable.ic_error
)