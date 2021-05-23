package com.yps.layani.admin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yps.layani.R
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class HomeActivity : AppCompatActivity() {
    private var userId: String = ""

//    private lateinit var txt_name: TextView
//    private lateinit var txt_email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       title = "Layani"

        val intent = intent
        userId = intent.getIntExtra("id", 0).toString()

//        txt_name = findViewById(R.id.tv_fullname)
//        txt_email = findViewById(R.id.tv_email)
//
//        getUser()

    }

//    private fun getUser() {
//        ApiService.loginApiCall().getUser(userId).enqueue(object : Callback<UserResponse>{
//            override fun onResponse(
//                call: Call<UserResponse>,
//                response: Response<UserResponse>
//            ) {
//                Log.d("Response User ::::", response.body().toString())
//                if (response.body()!!.status){
//                    txt_name.setText(response.body()!!.data.fullName)
//                    txt_email.setText(response.body()!!.data.email)
//                }
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
////                            Log.d("error::::",t?.message)
//            }
//
//        })
//    }

}