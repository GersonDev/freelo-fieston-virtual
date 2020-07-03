package com.spydevs.fiestonvirtual.ui.codeverification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
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
        viewModel.apply {
            isSuccessCode.observe(
                this@CodeVerificationActivity, Observer(::codeVerificationState)
            )
            error.observe(
                this@CodeVerificationActivity, Observer(::codeVerificationState)
            )
            loading.observe(
                this@CodeVerificationActivity, Observer(::codeVerificationState)
            )
        }

    }

    private fun setUpCodeButton() {
        codeButton.setOnClickListener {
            viewModel.verifyCode(codeVerification_et.text.toString())
        }
    }

    private fun codeVerificationState(result: CodeVerificationResult) {
        when (result) {
            is CodeVerificationResult.Loading -> {
                if (result.show) {
                    this.dialogProgress.show()
                } else {
                    this.dialogProgress.dismiss()
                }
            }
            CodeVerificationResult.CodeVerificationSuccessful -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            is CodeVerificationResult.CodeVerificationError -> {
                result.errorResponse.let {
                    this.setupAlertDialog(
                        it.title,
                        it.message
                    ) {}
                }

            }
        }

    }

}
