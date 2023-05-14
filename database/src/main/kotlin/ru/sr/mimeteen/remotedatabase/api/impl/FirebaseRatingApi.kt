package ru.sr.mimeteen.remotedatabase.api.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.sr.mimeteen.remotedatabase.api.RatingApi
import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.mimeteen.remotedatabase.model.TableRemoteDatabase
import ru.sr.mimeteen.remotedatabase.FirebaseNotAuth

class FirebaseRatingApi(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth,
) : RatingApi {
    override suspend fun setNewRating(rating: RatingDto) {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        database.reference
            .child(TableRemoteDatabase.RatingTable.name)

            .child(currentUser.uid)
            .setValue(
                rating.copy(
                    userName = currentUser.displayName ?: "User_${currentUser.uid}",
                    userId = currentUser.uid,
                    userAvatar = currentUser.photoUrl.toString()
                )
            ).await()

    }

    override suspend fun getTopTenRating(): List<RatingDto> {
        return database.reference
            .child(TableRemoteDatabase.RatingTable.name)
            .orderByChild(SORT_BY_STEPS)
            .limitToFirst(LIMIT_RATING_PAGE)
            .get()
            .await()
            .children.map { dataSnapshot ->
                val rating = dataSnapshot.getValue(RatingDto::class.java)
                    ?: throw NullPointerException("Пользователь не имеет резултатов")
                rating
            }
    }

    override suspend fun getRatingByUseID(): RatingDto? {

        val userId = auth.currentUser?.uid ?: throw FirebaseNotAuth()

        return database.reference
            .child(TableRemoteDatabase.RatingTable.name)
            .child(userId)
            .get()
            .await()
            .getValue(RatingDto::class.java)
    }

    override suspend fun showMyRating(): List<RatingDto> {
        val userId = auth.currentUser?.uid ?: throw FirebaseNotAuth()

        val userRating = database.reference
            .child(TableRemoteDatabase.RatingTable.name)
            .child(userId)
            .get()
            .await()
            .getValue(RatingDto::class.java) ?: return emptyList()

        return database.reference
            .child(TableRemoteDatabase.RatingTable.name)
            .orderByChild(SORT_BY_STEPS)
            .endAt(userRating.steps.toDouble())
            .get()
            .await()
            .children.map { dataSnapshot ->
                val rating = dataSnapshot.getValue(RatingDto::class.java)
                    ?: throw NullPointerException("Пользователь не имеет резултатов")
                rating
            }
    }

    private companion object {
        const val LIMIT_RATING_PAGE = 10
        const val SORT_BY_STEPS = "steps"

    }
}