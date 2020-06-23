package com.spydevs.fiestonvirtual.util.formatter

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class Base64ImageFormattingStrategy: ImageFormattingStrategy {
    override fun format(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        if (bitmap.width > 768 && bitmap.height > 768) {
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 768, 768, true)
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        } else {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        }
        val byteArrayImage = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT)
    }
}