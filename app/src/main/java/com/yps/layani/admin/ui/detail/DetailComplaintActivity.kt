package com.yps.layani.admin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yps.layani.R
import android.content.Intent
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.graphics.Bitmap
import android.widget.ImageButton
import com.yps.layani.DetailInfoActivity
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.Information

class DetailComplaintActivity : AppCompatActivity() {

    private lateinit var image_button : ImageButton
    private lateinit var show_img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_complaint)

        supportActionBar?.apply {
            title = "Layani"
            setDisplayHomeAsUpEnabled(true)
        }

        image_button = findViewById(R.id.image_button)
        show_img = findViewById(R.id.show_img)

        image_button.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123   )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123){
            var bmp = data?.extras?.get("data") as Bitmap
            show_img.setImageBitmap(bmp)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}