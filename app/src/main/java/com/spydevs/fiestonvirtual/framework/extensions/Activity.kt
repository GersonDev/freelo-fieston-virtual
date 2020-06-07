package com.spydevs.fiestonvirtual.framework.extensions

import android.app.Activity
import android.content.DialogInterface
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import com.spydevs.fiestonvirtual.R

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}