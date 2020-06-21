package com.spydevs.fiestonvirtual.framework.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import androidx.core.app.ActivityCompat.startActivityForResult
import com.spydevs.fiestonvirtual.ui.main.camera.CameraActivity

fun Context.openSettings() {
    val intent = Intent().apply {
        val uri =
            Uri.fromParts("package", this@openSettings.packageName, null)
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = uri
    }
    this.startActivity(intent)
}