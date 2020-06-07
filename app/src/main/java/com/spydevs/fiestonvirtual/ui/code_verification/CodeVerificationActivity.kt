package com.spydevs.fiestonvirtual.ui.code_verification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_code_verification.*

class CodeVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)
        setupViewListeners()
    }

    fun setupViewListeners() {
        codeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
