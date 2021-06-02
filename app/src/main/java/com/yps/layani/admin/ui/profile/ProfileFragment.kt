package com.yps.layani.admin.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.User
import com.yps.layani.admin.preferences.UserPreference
import com.yps.layani.admin.response.UserResponse
import com.yps.layani.admin.ui.home.ViewModelFactory
import com.yps.layani.admin.ui.leaderboard.LeaderboardViewModel
import com.yps.layani.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileViewModel: ProfileViewModel = ViewModelProvider(
            this@ProfileFragment, ViewModelFactory(requireActivity().application)
        ).get(ProfileViewModel::class.java)

        val pref = UserPreference(requireContext())
        profileViewModel.getProfileUser(pref.token)

        profileViewModel.userProfile.observe(viewLifecycleOwner, { loadProfile ->
            if (loadProfile != null) {
                binding.profileName.text = loadProfile.name
                binding.profileEmail.text = loadProfile.email
            }
        })
    }
}

//        val name = pref.user.name
//        binding.profileName.text = name
//
//        val email = pref.user.email
//        binding.profileEmail.text = email
//
//        val photo = pref.user.photo
//        binding.profileImg. = photo.toString()
//
//        val exp = pref.user.exp
//        binding.tvExp.text = exp.toString()
//
//        val rank = pref.user.email
//        binding.tvLevel.text = rank
//
//    }
