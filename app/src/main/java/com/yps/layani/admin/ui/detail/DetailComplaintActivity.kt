package com.yps.layani.admin.ui.detail

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yps.layani.R
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.graphics.Bitmap
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yps.layani.DetailInfoActivity
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.Information

class DetailComplaintActivity : AppCompatActivity() {

    private lateinit var image_button: ImageButton
    private lateinit var show_img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_complaint)

        supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(true)
        }

        image_button = findViewById(R.id.image_button)
        show_img = findViewById(R.id.show_img)

        image_button.setOnClickListener {
            checkCamera()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION ->
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(i, 123)
                } else {
                    Toast.makeText(
                        this,
                        "Please grant camera permission to use the Camera",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun checkCamera() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION
            )
        } else {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            var bmp = data?.extras?.get("data") as Bitmap
            show_img.setImageBitmap(bmp)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val CAMERA_PERMISSION = 3
    }
}