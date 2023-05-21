package ru.sr.nineteen.data

import android.content.Context
import ru.sr.nineteen.domain.UserIdProvider

class UserIdProviderImpl(context: Context): UserIdProvider {

    private val preferenceToken =
        context.getSharedPreferences(USER_ID_SHARED_NAME, Context.MODE_PRIVATE)

    override fun putUserId(token: String) {
        preferenceToken.edit().putString(User_ID, token).apply()
    }

    override fun clearUserId() {
        preferenceToken.edit().clear().apply()
    }

    override fun getUserId(): String? =
        preferenceToken.getString(User_ID, null)


    override fun userIdContain(): Boolean {
        return getUserId() != null
    }

    companion object {
        private const val USER_ID_SHARED_NAME = "Pref_user_id"
        private const val User_ID = "user_id"
    }

}