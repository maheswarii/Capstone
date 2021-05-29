package com.yps.layani.admin.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.response.UserResponse
import com.yps.layani.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var txt_name: TextView
    private lateinit var txt_email: TextView
    var userId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

//        val intent = getIntent()
//        userId = intent.getIntExtra("id", 0).toString()

        txt_name = binding.profileName
        txt_email = binding.profileEmail

        getUser()

        _binding = FragmentProfileBinding.inflate(inflater, container, true)
        return binding.root

    }

    private fun getUser() {
        ApiService.loginApiCall().getUser(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                Log.d("Response User ::::", response.body().toString())
                if (response.body()!!.status){
                    txt_name.setText(response.body()!!.data.name)
                    txt_email.setText(response.body()!!.data.email)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                            Log.d("error::::",t?.message)
            }

        })
    }
}