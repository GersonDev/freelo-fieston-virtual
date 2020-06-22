package com.spydevs.fiestonvirtual.util.formatter

import android.graphics.Bitmap

/**
 * Strategy for formatting a bitmap to string
 * by using your desired algorithm
 */
interface ImageFormattingStrategy {

    /**
     * Format a bitmap to a string
     * @param bitmap any bitmap to be formatted
     * @return your formatted bitmap in string format
     */
    fun format(bitmap: Bitmap): String
}