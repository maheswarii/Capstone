package com.yps.layani.admin.ui.leaderboard

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.model.Stats
import com.yps.layani.admin.response.StatsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaderboardViewModel(application: Application) : AndroidViewModel(application) {
    val stats = MutableLiveData<List<Stats>>()

    fun getLeaderboard(token: String) {
        val request = ApiService.loginApiCall()
        request.getListLeaderboard("Bearer $token").enqueue(object : Callback<StatsResponse> {
            override fun onResponse(
                call: Call<StatsResponse>,
                response: Response<StatsResponse>
            ) {
                stats.postValue(response.body()?.users ?: listOf<Stats>())
                Log.d(
                    "onResponse",
                    "getUser: ${response.body()} - ${stats.value?.size}"
                )
            }

            override fun onFailure(call: Call<StatsResponse>, t: Throwable) {
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