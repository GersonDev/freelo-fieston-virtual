package com.spydevs.fiestonvirtual.ui.codeverification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_code_verification.*
import org.koin.android.ext.android.inject

class CodeVerificationActivity : AppCompatActivity() {

    private val dialogProgress by lazy {
        setupLoadingAlertDialog()
    }
    private val viewModel: CodeVerificationViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)
        setUpCodeButton()
        setUpViewModel()

    }

    private fun setUpViewModel() {
        viewModel.isSuccessCode.observe(this, isSuccessCodeObserver)
    }

    private fun setUpCodeButton() {
        codeButton.setOnClickListener {
            dialogProgress.show()
            viewModel.verifyCode(codeVerification_et.text.toString().trim())
        }
    }

    private val isSuccessCodeObserver = Observer<Boolean> {
        dialogProgress.dismiss()
        if (it) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
