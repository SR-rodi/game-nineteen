package ru.sr.mimeteen.remotedatabase.api

import android.net.Uri

interface UploadApi {

    suspend fun uLoadImage(userId: String, byteArray: ByteArray): Uri
}