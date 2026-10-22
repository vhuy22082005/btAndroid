package com.example.btandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.btandroid.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayData()
        setupListeners()
    }

    private fun displayData() {
        val name = intent.getStringExtra("EXTRA_NAME") ?: "Chưa có"
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: "Chưa có"
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: "Chưa có"
        val msv = intent.getStringExtra("EXTRA_MSV") ?: "Chưa có"
        val className = intent.getStringExtra("EXTRA_CLASS") ?: "Chưa có"

        binding.tvName.text = "Họ và tên: $name"
        binding.tvEmail.text = "Email: $email"
        binding.tvPhone.text = "Số điện thoại: $phone"
        binding.tvMsv.text = "Mã sinh viên: $msv"
        binding.tvClass.text = "Lớp: $className"
    }

    private fun setupListeners() {
        binding.btnBackToRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        }
    }
}
