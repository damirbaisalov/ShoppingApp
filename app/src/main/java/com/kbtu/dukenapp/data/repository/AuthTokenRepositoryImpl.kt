package com.kbtu.dukenapp.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.kbtu.dukenapp.domain.repository.AuthTokenRepository

class AuthTokenRepositoryImpl(context: Context) :
    AuthTokenRepository {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun getAuthToken(): Int {
        return sharedPreferences.getInt(AUTH_TOKEN, 0)
    }
 
    override fun storeAuthToken(token: Int) {
        editor.putInt(AUTH_TOKEN, token)
        editor.apply()
    }

    override fun clearAuthToken() {
        editor.remove(AUTH_TOKEN).apply()
    }

    override fun isUserAuth(): Boolean {
        return sharedPreferences.getInt(AUTH_TOKEN, 0) != 0
    }

    // Check if the user is logged in
    override fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    override fun setLoggedIn(isLoggedIn: Boolean, accessToken: String, userId: Int) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            putString(KEY_ACCESS_TOKEN, accessToken)
            putInt(KEY_USER_ID, userId)
            apply()
        }
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_USER_ID, -1)
    }

    override fun logout() {
        sharedPreferences.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, false)
            putString(KEY_ACCESS_TOKEN, null)
            putInt(KEY_USER_ID, -1)
            apply()
        }
    }

    companion object {
        private const val AUTH_TOKEN: String = "AUTH_TOKEN"
        private const val PREFERENCES_NAME: String = "OnlineStorePref"

        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_USER_ID = "user_id"
    }
}

