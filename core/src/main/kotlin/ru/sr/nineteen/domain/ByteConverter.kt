package ru.sr.nineteen.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.InputStream

interface ByteConvertor {
    fun fromUri(uri: Uri): ByteArray
}

class ByteConvertorImpl(private val context: Context) : ByteConvertor {

    override fun fromUri(uri: Uri): ByteArray {

        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.WEBP, 5, stream)
        return stream.toByteArray()
    }
}