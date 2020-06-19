package com.spydevs.fiestonvirtual.framework.extensions

import android.app.Activity
import androidx.appcompat.app.AlertDialog

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