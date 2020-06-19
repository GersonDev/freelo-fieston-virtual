package com.spydevs.fiestonvirtual.ui.codeverification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_code_verification.*
import org.koin.android.ext.android.inject

class CodeVerificationActivity : AppCompatActivity() {

    private val locationViewModel: CodeVerificationViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)
        setUpCodeButton()

        //TODO refactor in the next PR...
        locationViewModel.isSuccessCode.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        })
    }

    private fun setUpCodeButton() {
        //TODO refactor in the next PR...
        codeButton.setOnClickListener {
            locationViewModel.verifyCode("946962")
        }
    }
}
