package com.olivia.plant.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory


fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
    // Convert the byte array to a Bitmap
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}