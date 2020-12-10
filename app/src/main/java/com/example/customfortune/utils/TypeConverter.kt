package com.example.customfortune.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream

object TypeConverter {
    fun getStringFromBitmap(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val bytesArray = stream.toByteArray()
        return Base64.encodeToString(bytesArray, Base64.DEFAULT)
    }

    fun getBitmapFromString(string: String): Bitmap {
        val imageByteArray = Base64.decode(string, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
    }
}