package com.dogimagegenerator.app.utils

import android.view.View

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