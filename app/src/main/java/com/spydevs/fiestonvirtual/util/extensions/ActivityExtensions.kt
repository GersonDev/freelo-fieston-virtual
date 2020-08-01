package com.spydevs.fiestonvirtual.util.extensions

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity.Companion.REQUEST_TO_MEDIA

fun Activity.setupAlertDialog(
    title: String? = null,
    message: String,
    textPositiveButton: String? = getString(R.string.alertDialog_ok),
    onPositiveButtonClick: (() -> Unit)? = null,
    textNegativeButton: String? = null,
    onNegativeButtonClick: (() -> Unit)? = null,
    setCancelable: Boolean = false
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(setCancelable)
        .setNegativeButton(
            textNegativeButton
        ) { _, _ ->
            onNegativeButtonClick?.let { it() }
        }
        .setPositiveButton(
            textPositiveButton
        ) { _, _ ->
            onPositiveButtonClick?.let { it() }
        }
        .create()
        .show()
}

fun Activity.openImageGalleryExternalApp() {
    val intent = Intent(Intent.ACTION_PICK).apply {
        data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }
    startActivityForResult(intent, REQUEST_TO_MEDIA)
}

fun Activity.openVideoGalleryExternalApp() {
    val intent = Intent(Intent.ACTION_PICK).apply {
        data = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    }
    startActivityForResult(intent, REQUEST_TO_MEDIA)
}

fun Activity.openAudioGalleryExternalApp() {
    val intent = Intent(Intent.ACTION_PICK).apply {
        data = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    }
    startActivityForResult(intent, REQUEST_TO_MEDIA)
}

fun Activity.openImageAndVideoGalleryExternalApp() {
    val intent = Intent(Intent.ACTION_PICK).apply {
        data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        type = "image/* video/*"
    }
    startActivityForResult(intent, REQUEST_TO_MEDIA)
}

fun Activity.setupLoadingAlertDialog(
    cancelable: Boolean = false
) =
    AlertDialog.Builder(this)
        .setCancelable(cancelable)
        .setView(R.layout.layout_loading_dialog)
        .create()