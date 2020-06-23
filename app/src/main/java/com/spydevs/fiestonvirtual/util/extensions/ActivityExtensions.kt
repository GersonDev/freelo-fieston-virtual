package com.spydevs.fiestonvirtual.util.extensions

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity.Companion.REQUEST_TO_MEDIA

fun Activity.setupAlertDialog(title: String = "Error", message: String, onPositiveButtonClick:() -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setNegativeButton(getString(android.R.string.cancel)) { _, _ ->

        }
        .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
            onPositiveButtonClick()
        }
        .create()
        .show()
}

fun Activity.openGalleryExternalApp() {
    val intent = Intent(Intent.ACTION_PICK).apply {
        data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
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