package com.yps.layani.admin.ui.detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.yps.layani.admin.model.Information
import com.yps.layani.databinding.ActivityDetailInfoBinding


class DetailInfoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailInfoBinding

    companion object {
        const val EXTRA_INFO = "extra_info"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Detail"
            setDisplayHomeAsUpEnabled(true)
        }
        val info = intent.getParcelableExtra<Information>(EXTRA_INFO) as Information

        Glide.with(this)
            .load(info.photo)
            .into(binding.imageDetail)

        binding.tvTitle.text = info.title
        binding.tvDetail.text = info.content_detail_info

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}