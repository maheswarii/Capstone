package com.yps.layani.admin.ui.home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.response.ComplaintResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val users = MutableLiveData<List<Complaint>>()

    fun getUserComplaint(token: String) {
        val request = ApiService.loginApiCall()
        request.getListComplaint("Bearer $token").enqueue(object : Callback<ComplaintResponse> {
            override fun onResponse(
                call: Call<ComplaintResponse>,
                response: Response<ComplaintResponse>
            ) {
                users.postValue(response.body()?.complaints ?: listOf<Complaint>())
                Log.d(
                    "onResponse",
                    "getUser: ${response.body()} - ${users.value?.size}"
                )
            }

            override fun onFailure(call: Call<ComplaintResponse>, t: Throwable) {
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