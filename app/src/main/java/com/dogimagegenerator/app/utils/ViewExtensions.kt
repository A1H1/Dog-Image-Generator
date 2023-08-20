package com.dogimagegenerator.app.utils

import android.view.View
import android.widget.ImageView
import com.dogimagegenerator.app.R

fun View.setDebounceClickListener(debounceTime: Long = 1000L, action: () -> Unit) {
    setOnClickListener {
        when {
            tag != null && (tag as Long) > System.currentTimeMillis() -> return@setOnClickListener
            else -> {
                tag = System.currentTimeMillis() + debounceTime
                action()
            }
        }
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    GlideApp
        .with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_loading_spinner)
        .into(this)
}
