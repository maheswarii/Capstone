package com.yps.layani.admin.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.LoginRequest
import com.yps.layani.admin.model.RegisterRequest
import com.yps.layani.admin.model.User

class UserPreference constructor(private val mCtx: Context) {
    val isLoggedIn: String
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("status", null)!!
        }

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
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences.getString("id", null)!!,
                sharedPreferences.getString("name", null)!!,
                sharedPreferences.getString("email", null)!!,
                sharedPreferences.getInt("photo", -1),
                sharedPreferences.getInt("exp", -1),
                sharedPreferences . getString ("rank", null)!!
            )
        }

    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("email", user.email)

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