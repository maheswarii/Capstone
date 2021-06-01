package com.yps.layani.admin.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yps.layani.admin.ui.graph.GraphViewModel

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(application) as T
            }else if ( modelClass.isAssignableFrom(GraphViewModel::class.java)) {
                return GraphViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}