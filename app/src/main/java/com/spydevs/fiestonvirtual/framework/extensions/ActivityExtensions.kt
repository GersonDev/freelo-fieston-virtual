package com.spydevs.fiestonvirtual.framework.extensions

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import com.spydevs.fiestonvirtual.ui.main.MainActivity.Companion.REQUEST_TO_MEDIA
import com.spydevs.fiestonvirtual.ui.main.camera.CameraActivity

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