package com.spydevs.fiestonvirtual.util.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Context.openSettings() {
    val intent = Intent().apply {
        val uri =
            Uri.fromParts("package", this@openSettings.packageName, null)
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = uri
    }
    this.startActivity(intent)
}