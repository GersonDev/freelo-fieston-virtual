package com.spydevs.fiestonvirtual.util.extensions

import androidx.appcompat.app.AlertDialog

fun AlertDialog.show(loading: Boolean) {
    if (loading) {
        this.show()
    } else {
        this.dismiss()
    }
}