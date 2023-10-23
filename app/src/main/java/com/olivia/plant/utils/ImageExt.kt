package com.olivia.plant.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream


fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
    // Convert the byte array to a Bitmap
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

// Asynchronous function to convert a Bitmap to a Base64 encoded string
suspend fun bitmapToBase64Async(bitmap: Bitmap, quality: Int, progressDialog: SpotsDialog): String {
    return withContext(Dispatchers.Default) {
        withContext(Dispatchers.Main) {
            progressDialog.show()
        }
        return@withContext try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        } finally {
            withContext(Dispatchers.Main) {
                progressDialog.dismiss()
            }
        }
    }
}

fun bitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

// Function to compress a Bitmap and return a new compressed Bitmap
fun compressBitmap(originalBitmap: Bitmap, quality: Int): Bitmap {
    val stream = ByteArrayOutputStream()
    originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)

    val byteArray = stream.toByteArray()

    // Decode the compressed data back into a Bitmap
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

// Function to compress and save a bitmap asynchronously
fun compressBitmapToBase63(
    bitmap: Bitmap,
    quality: Int,
): String {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)

    // Optionally, you can also save the compressed bitmap to a file
    val byteArray = stream.toByteArray()

    // Convert the byte array to a Base64 encoded string
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}