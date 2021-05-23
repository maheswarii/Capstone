package com.yps.layani.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yps.layani.R

class HomeActivity : AppCompatActivity() {

    private var userId: String = ""

//    private lateinit var txt_name: TextView
//    private lateinit var txt_email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_graph, R.id.nav_notifications, R.id.nav_profile
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

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