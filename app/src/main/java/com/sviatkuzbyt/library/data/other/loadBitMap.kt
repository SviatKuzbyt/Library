package com.sviatkuzbyt.library.data.other

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException

fun getBitmapFromAsset(context: Context, fileName: String): Bitmap? {
    return try {
        context.assets.open("booksImg/$fileName").use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: IOException) {
        null
    }
}

fun getLowBitmapFromAsset(context: Context, fileName: String): Bitmap? {
    return try {
        context.assets.open("booksImg/$fileName").use { inputStream ->
            val options = BitmapFactory.Options()
            options.inSampleSize = 2
            BitmapFactory.decodeStream(inputStream, null, options)
        }
    } catch (e: IOException) {
        null
    }
}