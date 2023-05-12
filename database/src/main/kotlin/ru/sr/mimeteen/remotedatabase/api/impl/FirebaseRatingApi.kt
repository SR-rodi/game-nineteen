package ru.sr.mimeteen.remotedatabase.api.impl

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.sr.mimeteen.remotedatabase.api.RatingApi
import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.mimeteen.remotedatabase.model.TableRemoteDatabase
import ru.sr.mimeteen.remotedatabase.model.UserDto

class FirebaseRatingApi(
    private val database: FirebaseDatabase,
) : RatingApi {
    override suspend fun setNewRating(rating: RatingDto) {
        database.reference.child(TableRemoteDatabase.RatingTable.name).child(rating.userId)
            .setValue(rating)
    }

    override suspend fun getAllRating(): List<RatingDto> {
        return database
            .reference
            .child(TableRemoteDatabase.RatingTable.name)
            .limitToFirst(10).get()
            .await()
            .children.map { dataSnapshot ->
              val rating =   dataSnapshot.getValue(RatingDto::class.java)
                    ?: throw NullPointerException("Пользователь не имеет резултатов")
                Log.e("Kart","rating = $rating")
                rating
            }
    }

    override suspend fun getRatingByUseID(userId: String): RatingDto {

        return database.reference.child(TableRemoteDatabase.RatingTable.name).child(userId).get()
            .await()
            .getValue(RatingDto::class.java)
            ?: throw NullPointerException("Пользователь не имеет резултатов")
    }
}