package com.yps.layani.admin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yps.layani.R

class DetailDoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_done)

//        val textView = findViewById<TextView>(R.id.tv_note_detail).apply {
//            text = pesan
//        }

        setTitle("Pesan")
    }
}