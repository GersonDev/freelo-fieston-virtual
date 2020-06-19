package com.spydevs.fiestonvirtual.util

import android.Manifest
import android.annotation.TargetApi
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.webkit.MimeTypeMap
import java.io.*
import java.util.*

object NativeGallery {
    fun SaveMedia(
        context: Context,
        mediaType: Int,
        filePath: String,
        directoryName: String
    ) {
        val originalFile = File(filePath)
        if (!originalFile.exists()) {
            Log.e("Unity", "Original media file is missing or inaccessible!")
            return
        }
        val pathSeparator = filePath.lastIndexOf('/')
        val extensionSeparator = filePath.lastIndexOf('.')
        val filename =
            if (pathSeparator >= 0) filePath.substring(pathSeparator + 1) else filePath
        val extension =
            if (extensionSeparator >= 0) filePath.substring(extensionSeparator + 1) else ""

        // Credit: https://stackoverflow.com/a/31691791/2373034
        val mimeType = if (extension.length > 0) MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(extension.toLowerCase(Locale.ENGLISH)) else null
        val values = ContentValues()
        values.put(MediaStore.MediaColumns.TITLE, filename)
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
        values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000)
        if (mimeType != null && mimeType.length > 0) values.put(
            MediaStore.MediaColumns.MIME_TYPE,
            mimeType
        )
        val externalContentUri: Uri
        externalContentUri =
            if (mediaType == 0) MediaStore.Images.Media.EXTERNAL_CONTENT_URI else if (mediaType == 1) MediaStore.Video.Media.EXTERNAL_CONTENT_URI else MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        // Android 10 restricts our access to the raw filesystem, use MediaStore to save media in that case
        if (Build.VERSION.SDK_INT >= 29) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/$directoryName")
            values.put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis())
            values.put(MediaStore.MediaColumns.IS_PENDING, true)
            val uri =
                context.contentResolver.insert(externalContentUri, values)
            if (uri != null) {
                try {
                    if (WriteFileToStream(
                            originalFile,
                            context.contentResolver.openOutputStream(uri)
                        )
                    ) {
                        values.put(MediaStore.MediaColumns.IS_PENDING, false)
                        context.contentResolver.update(uri, values, null, null)
                    }
                } catch (e: Exception) {
                    Log.e("Unity", "Exception:", e)
                    context.contentResolver.delete(uri, null, null)
                }
            }
        } else {
            val directory = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                directoryName
            )
            directory.mkdirs()
            var file: File
            var fileIndex = 1
            val filenameWithoutExtension =
                if (extension.length > 0) filename.substring(
                    0,
                    filename.length - extension.length - 1
                ) else filename
            var newFilename = filename
            do {
                file = File(directory, newFilename)
                newFilename = filenameWithoutExtension + fileIndex++
                if (extension.length > 0) newFilename += ".$extension"
            } while (file.exists())
            try {
                if (WriteFileToStream(originalFile, FileOutputStream(file))) {
                    values.put(MediaStore.MediaColumns.DATA, file.absolutePath)
                    context.contentResolver.insert(externalContentUri, values)
                    Log.d("Unity", "Saved media to: " + file.path)

                    // Refresh the Gallery
                    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    mediaScanIntent.data = Uri.fromFile(file)
                    context.sendBroadcast(mediaScanIntent)
                }
            } catch (e: Exception) {
                Log.e("Unity", "Exception:", e)
            }
        }
    }

    private fun WriteFileToStream(file: File, out: OutputStream?): Boolean {
        try {
            val `in`: InputStream = FileInputStream(file)
            try {
                val buf = ByteArray(1024)
                var len: Int
                while (`in`.read(buf).also { len = it } > 0) out!!.write(buf, 0, len)
            } finally {
                try {
                    `in`.close()
                } catch (e: Exception) {
                    Log.e("Unity", "Exception:", e)
                }
            }
        } catch (e: Exception) {
            Log.e("Unity", "Exception:", e)
            return false
        } finally {
            try {
                out!!.close()
            } catch (e: Exception) {
                Log.e("Unity", "Exception:", e)
            }
        }
        return true
    }

    fun MediaDeleteFile(
        context: Context,
        path: String,
        mediaType: Int
    ) {
        if (mediaType == 0) context.contentResolver.delete(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            MediaStore.Images.Media.DATA + "=?",
            arrayOf(path)
        ) else if (mediaType == 1) context.contentResolver.delete(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            MediaStore.Video.Media.DATA + "=?",
            arrayOf(path)
        ) else context.contentResolver.delete(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            MediaStore.Audio.Media.DATA + "=?",
            arrayOf(path)
        )
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun CheckPermission(context: Context, readPermissionOnly: Boolean): Int {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return 1
        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            if (readPermissionOnly || context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) return 1
        }
        return 0
    }

    // Credit: https://stackoverflow.com/a/35456817/2373034
    fun OpenSettings(context: Context) {
        val uri =
            Uri.fromParts("package", context.packageName, null)
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = uri
        context.startActivity(intent)
    }

    fun CanSelectMultipleMedia(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
    }

    private fun GetImageMetadata(path: String?): BitmapFactory.Options? {
        return try {
            val result = BitmapFactory.Options()
            result.inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, result)
            result
        } catch (e: Exception) {
            Log.e("Unity", "Exception:", e)
            null
        }
    }

    // Credit: https://stackoverflow.com/a/30572852/2373034
    private fun GetImageOrientation(context: Context, path: String?): Int {
        try {
            val exif = ExifInterface(path)
            val orientationEXIF = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )
            if (orientationEXIF != ExifInterface.ORIENTATION_UNDEFINED) return orientationEXIF
        } catch (e: Exception) {
        }
        var cursor: Cursor? = null
        try {
            cursor = context.contentResolver.query(
                Uri.fromFile(File(path)),
                arrayOf(MediaStore.Images.Media.ORIENTATION),
                null,
                null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val orientation =
                    cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION))
                if (orientation == 90) return ExifInterface.ORIENTATION_ROTATE_90
                if (orientation == 180) return ExifInterface.ORIENTATION_ROTATE_180
                return if (orientation == 270) ExifInterface.ORIENTATION_ROTATE_270 else ExifInterface.ORIENTATION_NORMAL
            }
        } catch (e: Exception) {
        } finally {
            cursor?.close()
        }
        return ExifInterface.ORIENTATION_UNDEFINED
    }

    // Credit: https://gist.github.com/aviadmini/4be34097dfdb842ae066fae48501ed41
    private fun GetImageOrientationCorrectionMatrix(
        orientation: Int,
        scale: Float
    ): Matrix {
        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                matrix.postRotate(270f)
                matrix.postScale(scale, scale)
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                matrix.postRotate(180f)
                matrix.postScale(scale, scale)
            }
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                matrix.postRotate(90f)
                matrix.postScale(scale, scale)
            }
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> {
                matrix.postScale(-scale, scale)
            }
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> {
                matrix.postScale(scale, -scale)
            }
            ExifInterface.ORIENTATION_TRANSPOSE -> {
                matrix.postRotate(90f)
                matrix.postScale(-scale, scale)
            }
            ExifInterface.ORIENTATION_TRANSVERSE -> {
                matrix.postRotate(270f)
                matrix.postScale(-scale, scale)
            }
            else -> {
                matrix.postScale(scale, scale)
            }
        }
        return matrix
    }

    fun LoadImageAtPath(
        context: Context,
        path: String?,
        temporaryFilePath: String?,
        maxSize: Int
    ): String? {
        var path = path
        val metadata = GetImageMetadata(path) ?: return path
        var shouldCreateNewBitmap = false
        if (metadata.outWidth > maxSize || metadata.outHeight > maxSize) shouldCreateNewBitmap =
            true
        if (metadata.outMimeType != null && metadata.outMimeType != "image/jpeg" && metadata.outMimeType != "image/png") shouldCreateNewBitmap =
            true
        val orientation = GetImageOrientation(context, path)
        if (orientation != ExifInterface.ORIENTATION_NORMAL && orientation != ExifInterface.ORIENTATION_UNDEFINED) shouldCreateNewBitmap =
            true
        if (shouldCreateNewBitmap) {
            var bitmap: Bitmap? = null
            var out: FileOutputStream? = null
            try {
                // Credit: https://developer.android.com/topic/performance/graphics/load-bitmap.html
                var sampleSize = 1
                val halfHeight = metadata.outHeight / 2
                val halfWidth = metadata.outWidth / 2
                while (halfHeight / sampleSize >= maxSize || halfWidth / sampleSize >= maxSize) sampleSize *= 2
                val options = BitmapFactory.Options()
                options.inSampleSize = sampleSize
                options.inJustDecodeBounds = false
                bitmap = BitmapFactory.decodeFile(path, options)
                var scaleX = 1f
                var scaleY = 1f
                if (bitmap.width > maxSize) scaleX =
                    maxSize / bitmap.width.toFloat()
                if (bitmap.height > maxSize) scaleY =
                    maxSize / bitmap.height.toFloat()

                // Create a new bitmap if it should be scaled down or if its orientation is wrong
                val scale = if (scaleX < scaleY) scaleX else scaleY
                if (scale < 1f || orientation != ExifInterface.ORIENTATION_NORMAL && orientation != ExifInterface.ORIENTATION_UNDEFINED) {
                    val transformationMatrix =
                        GetImageOrientationCorrectionMatrix(orientation, scale)
                    val transformedBitmap = Bitmap.createBitmap(
                        bitmap,
                        0,
                        0,
                        bitmap.width,
                        bitmap.height,
                        transformationMatrix,
                        true
                    )
                    if (transformedBitmap != bitmap) {
                        bitmap.recycle()
                        bitmap = transformedBitmap
                    }
                }
                out = FileOutputStream(temporaryFilePath)
                if (metadata.outMimeType == null || metadata.outMimeType != "image/jpeg") bitmap!!.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    out
                ) else bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, out)
                path = temporaryFilePath
            } catch (e: Exception) {
                Log.e("Unity", "Exception:", e)
                try {
                    val temporaryFile = File(temporaryFilePath)
                    if (temporaryFile.exists()) temporaryFile.delete()
                } catch (e2: Exception) {
                }
            } finally {
                bitmap?.recycle()
                try {
                    out?.close()
                } catch (e: Exception) {
                }
            }
        }
        return path
    }

    fun GetImageProperties(context: Context, path: String?): String {
        val metadata = GetImageMetadata(path) ?: return ""
        var width = metadata.outWidth
        var height = metadata.outHeight
        var mimeType = metadata.outMimeType
        if (mimeType == null) mimeType = ""
        val orientationUnity: Int
        val orientation = GetImageOrientation(context, path)
        orientationUnity =
            if (orientation == ExifInterface.ORIENTATION_UNDEFINED) -1 else if (orientation == ExifInterface.ORIENTATION_NORMAL) 0 else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) 1 else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) 2 else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) 3 else if (orientation == ExifInterface.ORIENTATION_FLIP_HORIZONTAL) 4 else if (orientation == ExifInterface.ORIENTATION_TRANSPOSE) 5 else if (orientation == ExifInterface.ORIENTATION_FLIP_VERTICAL) 6 else if (orientation == ExifInterface.ORIENTATION_TRANSVERSE) 7 else -1
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90 || orientation == ExifInterface.ORIENTATION_ROTATE_270 || orientation == ExifInterface.ORIENTATION_TRANSPOSE || orientation == ExifInterface.ORIENTATION_TRANSVERSE
        ) {
            val temp = width
            width = height
            height = temp
        }
        return "$width>$height>$mimeType>$orientationUnity"
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun GetVideoProperties(context: Context?, path: String?): String {
        val metadataRetriever = MediaMetadataRetriever()
        metadataRetriever.setDataSource(path)
        var width =
            metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
        var height =
            metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
        var duration =
            metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        var rotation: String? = "0"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) rotation =
            metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION)
        if (width == null) width = "0"
        if (height == null) height = "0"
        if (duration == null) duration = "0"
        if (rotation == null) rotation = "0"
        return "$width>$height>$duration>$rotation"
    }
}