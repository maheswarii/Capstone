package com.yps.layani.admin.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.yps.layani.admin.model.User

class UserPreference constructor(private val mCtx: Context) {

    val token : String
        get() = mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).getString("token", "")!!

    fun saveToken(token:String) {
        mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit().apply()
        {
            putString("token", token)
            apply()
        }
    }

    val user: User
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
            return User(
                sharedPreferences.getString("name", "")!!,
                sharedPreferences.getString("email", "")!!,
                sharedPreferences.getInt("photo", 0),
                sharedPreferences.getInt("exp", 0),
                sharedPreferences . getString ("rank", "")!!
            )
        }

    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("name", user.name)
        editor.putString("email", user.email)
        editor.putInt("photo", user.photo)
        editor.putInt("exp", user.exp)
        editor.putString("rank", user.rank)

        editor.apply()

    }


    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "shared_pref"
        private var mInstance: UserPreference? = null

        @Synchronized
        fun getInstance(mCtx: Context): UserPreference {
            if (mInstance == null) {
                mInstance = UserPreference(mCtx)
            }
            return mInstance as UserPreference
        }
    }
}