package com.yps.layani.admin.ui.profile

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.model.User
import com.yps.layani.admin.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel (application: Application) : AndroidViewModel(application) {
    val userProfile = MutableLiveData<User>()

    fun getProfileUser(token: String) {
        val request = ApiService.loginApiCall()
        request.getUserProfile("Bearer $token").enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                userProfile.postValue(response.body()?.data)
                Log.d(
                    "onResponse",
                    "getUser: ${response.body()} - ${userProfile.value}"
                )
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(
                    getApplication(),
                    "Error: " + t.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("onFailure", t.message.toString())
            }

        })
    }
}