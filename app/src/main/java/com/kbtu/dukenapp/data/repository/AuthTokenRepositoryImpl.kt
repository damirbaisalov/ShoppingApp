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

    companion object {
        private const val AUTH_TOKEN: String = "AUTH_TOKEN"
        private const val PREFERENCES_NAME: String = "OnlineStorePref"

    }
}

