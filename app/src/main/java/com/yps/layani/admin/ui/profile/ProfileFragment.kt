package com.yps.layani.admin.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yps.layani.R
import com.yps.layani.admin.preferences.UserPreference
import com.yps.layani.admin.ui.home.ViewModelFactory
import com.yps.layani.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    companion object {
        private const val ARG_SECTION_PARCEL = "section_parcel"
    }

//    private lateinit var viewModel: ProfileViewModel

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
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
            val isStarted = false
            var progressStatus = 0
            var handler: Handler? = null

            if (loadProfile != null) {
                binding.profileName.text = loadProfile.name
                binding.profileEmail.text = loadProfile.email
                binding.tvLevel.text = loadProfile.rank

                handler = Handler(Handler.Callback {
                    if (isStarted) {
                        progressStatus++
                    }
                    binding.expProgress.progress = progressStatus
                    binding.textViewHorizontalProgress.text =
                        "${progressStatus}/${binding.expProgress.max}" + "%"
                    handler?.sendEmptyMessageDelayed(0, 100 )

                    true
                })

                handler.sendEmptyMessage(0)

            }
        })
    Toast.makeText(context, "profile fragment", Toast.LENGTH_SHORT).show()
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
