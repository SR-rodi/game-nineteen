package ru.sr.mimeteen.remotedatabase.api.impl

import android.net.Uri
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import ru.sr.mimeteen.remotedatabase.api.UploadApi

class FireBaseStorageApi(
    private val storage: StorageReference,
) : UploadApi {

    override suspend fun uLoadImage(userId: String, byteArray: ByteArray): Uri {
        val ref = storage.child("Avatars/avatar_$userId.jpg")

        val uploadTask = storage.child("Avatars/avatar_$userId.jpg").putBytes(byteArray)


        return uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) task.exception?.let { throw it }
            ref.downloadUrl
        }.await()

    }

}


