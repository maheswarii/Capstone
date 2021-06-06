package com.yps.layani.admin.ui.detail

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yps.layani.R
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.model.DetailRequest
import com.yps.layani.admin.response.DetailResponse
import com.yps.layani.admin.ui.home.HomeFragment
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailComplaintActivity : AppCompatActivity() {

    private lateinit var note: EditText
    private lateinit var imageButton: ImageButton
    private lateinit var showImg: ImageView
    private lateinit var done: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_complaint)

        supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(true)
        }

        note = findViewById(R.id.et_note)
        imageButton = findViewById(R.id.image_button)
        showImg = findViewById(R.id.show_img)
        done = findViewById(R.id.btn_done)
        onClick(done)

        imageButton.setOnClickListener {
            checkCamera()
        }

        done.setOnClickListener {
            val intent = Intent(this@DetailComplaintActivity, HomeFragment::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Anda mendapatkan Exp!", Toast.LENGTH_SHORT)
                .show()
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
                    val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
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
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val bmp = data?.extras?.get("data") as Bitmap
            showImg.setImageBitmap(bmp)
        }
    }

    private fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_done -> {
                if (validation()) {
                    val json = JSONObject()
                    json.put("note", note.text.toString())

                    ApiService.loginApiCall().doSolved(
                        DetailRequest(
                            note.text.toString()
                        )
                    ).enqueue(object : Callback<DetailResponse> {
                        override fun onResponse(
                            call: Call<DetailResponse>,
                            response: Response<DetailResponse>
                        ) {

                            Log.d("Response Solved::::", response.body().toString())
                            val detailResponse: DetailResponse = response.body()!!
                            if (detailResponse.status == "finished") {
                                finish()
                                val intent = Intent(
                                    this@DetailComplaintActivity,
                                    DetailDoneActivity::class.java
                                )
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "GA BERHASIL YEU",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                        }

                    })
                }
            }
        }
    }

    fun validation(): Boolean {
        var value = true

        val notes = note.text.toString().trim()

        if (notes.isEmpty()) {
            note.error = "Note required"
            note.requestFocus()
            value = false
        }
        return value
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val CAMERA_PERMISSION = 3
    }
}