package com.example.btandroid

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.btandroid.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()
        setupListeners()
    }

    private fun setupRealtimeValidation() {
        binding.etName.doAfterTextChanged {
            if (it.toString().trim().isNotEmpty()) binding.tilName.error = null
        }
        binding.etEmail.doAfterTextChanged {
            val email = it.toString().trim()
            if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tilEmail.error = null
            }
        }
        binding.etPhone.doAfterTextChanged {
            val phone = it.toString().trim()
            if (phone.length >= 10 && phone.all { c -> c.isDigit() }) {
                binding.tilPhone.error = null
            }
        }
        binding.etMsv.doAfterTextChanged {
            if (it.toString().trim().isNotEmpty()) binding.tilMsv.error = null
        }
        binding.etClass.doAfterTextChanged {
            if (it.toString().trim().isNotEmpty()) binding.tilClass.error = null
        }
    }

    private fun setupListeners() {
        binding.btnGoToDetail.setOnClickListener { showDetailView() }
        binding.btnBackToRegister.setOnClickListener { showRegisterView() }

        binding.btnRegister.setOnClickListener {
            if (validateFormTotal()) {
                displayDetailData()
                showDetailView()
                Snackbar.make(binding.root, "Đăng ký thành công!", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun validateFormTotal(): Boolean {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val msv = binding.etMsv.text.toString().trim()
        val cls = binding.etClass.text.toString().trim()

        var isValid = true

        if (name.isEmpty()) {
            binding.tilName.error = "Vui lòng nhập họ tên"
            isValid = false
        } else {
            binding.tilName.error = null
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không hợp lệ"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        if (phone.isEmpty() || phone.length < 10 || !phone.all { it.isDigit() }) {
            binding.tilPhone.error = "Số điện thoại không hợp lệ (tối thiểu 10 số)"
            isValid = false
        } else {
            binding.tilPhone.error = null
        }

        if (msv.isEmpty()) {
            binding.tilMsv.error = "Vui lòng nhập mã sinh viên"
            isValid = false
        } else {
            binding.tilMsv.error = null
        }

        if (cls.isEmpty()) {
            binding.tilClass.error = "Vui lòng nhập lớp"
            isValid = false
        } else {
            binding.tilClass.error = null
        }

        return isValid
    }

    private fun displayDetailData() {
        binding.tvName.text = "Họ và tên: ${binding.etName.text}"
        binding.tvEmail.text = "Email: ${binding.etEmail.text}"
        binding.tvPhone.text = "Số điện thoại: ${binding.etPhone.text}"
        binding.tvMsv.text = "Mã sinh viên: ${binding.etMsv.text}"
        binding.tvClass.text = "Lớp: ${binding.etClass.text}"
    }

    private fun showDetailView() {
        binding.layoutRegister.visibility = View.GONE
        binding.layoutDetail.visibility = View.VISIBLE
    }

    private fun showRegisterView() {
        binding.layoutRegister.visibility = View.VISIBLE
        binding.layoutDetail.visibility = View.GONE
    }
}
