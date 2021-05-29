package com.yps.layani.admin.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.yps.layani.admin.model.Complaint

class HomeViewModel (application: Application) : AndroidViewModel(application) {
    private val users = MutableLiveData<ArrayList<Complaint>>()

    fun loadSearchUser() : LiveData<ArrayList<Complaint>>{
        Log.d("loadComplaint", "users:${users.value?.size}")
        return users
    }

}