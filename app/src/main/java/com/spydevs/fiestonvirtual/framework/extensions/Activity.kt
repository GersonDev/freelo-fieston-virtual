package com.spydevs.fiestonvirtual.framework.extensions

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.spydevs.fiestonvirtual.R

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.setupLoadingAlertDialog(
    cancelable: Boolean = false
) =
    AlertDialog.Builder(this)
        .setCancelable(cancelable)
        .setView(R.layout.layout_loading_dialog)
        .create()
