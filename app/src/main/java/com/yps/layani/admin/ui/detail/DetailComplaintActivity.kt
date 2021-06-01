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
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailComplaintActivity : AppCompatActivity() {

    private lateinit var ed_note: EditText
    private lateinit var image_button: ImageButton
    private lateinit var show_img: ImageView
    private lateinit var btn_done: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_complaint)

        supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(true)
        }

        ed_note = findViewById(R.id.et_note)
        image_button = findViewById(R.id.image_button)
        show_img = findViewById(R.id.show_img)
        btn_done = findViewById(R.id.btn_done)

        image_button.setOnClickListener {
            checkCamera()
        }

        //btn_done.setOnClickListener(this)
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
            show_img.setImageBitmap(bmp)
        }
    }

//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.btn_done -> {
//                if (validation()) {
//                    val json = JSONObject()
//                    json.put("note", ed_note.text.toString())
//
//                    ApiService.loginApiCall().doSolved(
//                        DetailRequest(
//                            ed_note.text.toString()
//                        )
//                    ).enqueue(object : Callback<DetailResponse> {
//                        override fun onResponse(
//                            call: Call<DetailResponse>,
//                            response: Response<DetailResponse>
//                        ) {
//
//                            Log.d("Response Solved::::", response.body().toString())
//                            val detailResponse: DetailResponse = response.body()!!
//                            if (detailResponse.note == "done") {
//                                finish()
//                                val intent = Intent(this@DetailComplaintActivity, DetailDoneActivity::class.java)
//                                startActivity(intent)
//                            } else {
//                                Toast.makeText(
//                                    applicationContext,
//                                    response.body()!!.note,
//                                    Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        }
//
//                        override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
//                        }
//
//                    })
//                }
//            }
//        }
//    }

    fun validation(): Boolean {
        var value = true

        val note = ed_note.text.toString().trim()

        if (note.isEmpty()) {
            ed_note.error = "Note required"
            ed_note.requestFocus()
            value = false
        }
        return value
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val CAMERA_PERMISSION = 3
    }
}