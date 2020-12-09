package com.example.customfortune.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream

object TypeConverter {
    fun getStringFromBitmap(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val imageByteArray = stream.toByteArray()
        return String(imageByteArray)
    }

    fun getBitmapFromString(string: String): Bitmap {
        val imageByteArray = string.toByteArray()
        return BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
    }

    fun getBitmapFromDrawable(drawable: Drawable): Bitmap {
        return Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888)
    }
}