package com.yps.layani.admin.ui.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yps.layani.R
import com.yps.layani.admin.preferences.UserPreference
import com.yps.layani.admin.ui.detail.DetailComplaintActivity
import com.yps.layani.admin.ui.home.ViewModelFactory
import com.yps.layani.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {


//    private lateinit var viewModel: ProfileViewModel

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profile_image: ImageView
    private lateinit var imageButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.hide()

       imageButton = binding.imageButton
        profile_image = binding.profileImg

        imageButton.setOnClickListener {
            checkCamera()
        }

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            DetailComplaintActivity.CAMERA_PERMISSION ->
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(i, 123)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please grant camera permission to use the Camera",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun checkCamera() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                DetailComplaintActivity.CAMERA_PERMISSION
            )
        } else {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val bmp = data?.extras?.get("data") as Bitmap
            profile_image.setImageBitmap(bmp)
        }
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

    companion object {
        private const val ARG_SECTION_PARCEL = "section_parcel"
        private const val CAMERA_PERMISSION = 3
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
