package com.spydevs.fiestonvirtual.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface

object ImagesUtil {

    fun getImageOrientation(context: Context, uri: Uri): Int {
        val inputStream = context.contentResolver.openInputStream(uri)
        inputStream?.let {
            val exifInterface = ExifInterface(it)
            return exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
        }
        return ExifInterface.ORIENTATION_UNDEFINED
    }

    fun getImageAngleRotation(imagePath: String): Int {
        val exifInterface = ExifInterface(imagePath)
        val orientation: Int = exifInterface.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_NORMAL -> 0
            else -> 0
        }
    }

    fun getRotatedBitmap(bitmap: Bitmap, imageOrientation: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(imageOrientation.toFloat())
        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }

    fun showExifTag(context: Context, uri: Uri) {

        val inputStream = context.contentResolver.openInputStream(uri)

        inputStream?.let {
            val exifInterface = ExifInterface(inputStream)
            val imageLength = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0)
            val imageWidth = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0)
            val dateTime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME)
            val make = exifInterface.getAttribute(ExifInterface.TAG_MAKE)
            val model = exifInterface.getAttribute(ExifInterface.TAG_MODEL)
            val orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            val whiteBalance = exifInterface.getAttribute(ExifInterface.TAG_WHITE_BALANCE)
            val focalLength = exifInterface.getAttribute(ExifInterface.TAG_FOCAL_LENGTH)
            val flash = exifInterface.getAttribute(ExifInterface.TAG_FLASH)
            val gpsDatestamp = exifInterface.getAttribute(ExifInterface.TAG_GPS_DATESTAMP)
            val gpsTimestamp = exifInterface.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP)
            val latitude = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE)
            val latitudeRed = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF)
            val longitude = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)
            val longitudeRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF)
            val gpsProcessingMethod =
                exifInterface.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD)
            var latLong0 = 0.0
            var latLong1 = 0.0
            if (exifInterface.latLong != null && exifInterface.latLong!!.size == 2) {
                latLong0 = exifInterface.latLong!![0]
                latLong1 = exifInterface.latLong!![1]
            }
            val exif = "ExifInterface Information: " +
                    "\n imageLength: $imageLength " +
                    "\n imageWidth: $imageWidth " +
                    "\n dateTime: $dateTime " +
                    "\n make: $make " +
                    "\n model: $model " +
                    "\n orientation: $orientation " +
                    "\n whiteBalance: $whiteBalance " +
                    "\n focalLength: $focalLength " +
                    "\n flash: $flash " +
                    "\n gpsDatestamp: $gpsDatestamp " +
                    "\n gpsTimestamp: $gpsTimestamp " +
                    "\n latitude: $latitude " +
                    "\n latitudeRed: $latitudeRed " +
                    "\n longitude: $longitude " +
                    "\n longitudeRef: $longitudeRef " +
                    "\n gpsProcessingMethod: $gpsProcessingMethod " +
                    "\n latLong0: $latLong0 " +
                    "\n latLong1: $latLong1 "

            Toast.makeText(context, exif, Toast.LENGTH_LONG).show()
        }
    }
}