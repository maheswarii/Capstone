package com.yps.layani.admin.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yps.layani.R

class HomeActivity : AppCompatActivity() {

    var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userId = intent.getStringExtra("id").toString()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val args = Bundle().apply {
            putString("token", userId)
        }
        navController.setGraph(R.navigation.mobile_navigation, args)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_graph, R.id.nav_notifications, R.id.nav_profile
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        title = "Layani"

    }
}