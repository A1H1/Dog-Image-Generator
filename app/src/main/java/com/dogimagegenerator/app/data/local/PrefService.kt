package com.dogimagegenerator.app.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.dogimagegenerator.app.utils.AppConst.PREF_FILE
import com.dogimagegenerator.app.utils.AppConst.PREF_IMAGES

class PrefService(context: Context) {
    private var pref: SharedPreferences = context.getSharedPreferences(PREF_FILE, MODE_PRIVATE)

    fun saveImage(image: String) {
        var images: MutableList<String> = getImages().toMutableList()
        if (images.size > 20) {
            images = images.take(19).toMutableList()
        }
        images.add(image)
        val pref = this.pref.edit()
        pref.putStringSet(PREF_IMAGES, images.toSet())
        pref.apply()
    }

    fun getImages(): List<String> =
        pref.getStringSet(PREF_IMAGES, emptySet())?.toList() ?: emptyList()

    fun clearImages() {
        val pref = this.pref.edit()
        pref.remove(PREF_IMAGES)
        pref.apply()
    }
}