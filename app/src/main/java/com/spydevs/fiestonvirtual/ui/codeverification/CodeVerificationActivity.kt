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
        subscribeToSuccessCode()
        subscribeToError()
        subscribeToLoading()
        setUpCodeButton()

    }

    private fun subscribeToSuccessCode() {
        viewModel.isSuccessCode.observe(
            this@CodeVerificationActivity,
            Observer {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        )
    }

    private fun subscribeToError() {
        viewModel.error.observe(
            this@CodeVerificationActivity,
            Observer {
                (it as CodeVerificationResult.CodeVerificationError).errorResponse.let { errorResponse ->
                    this.setupAlertDialog(
                        errorResponse.title,
                        errorResponse.message
                    ) {}
                }
            }
        )
    }

    private fun subscribeToLoading() {
        viewModel.loading.observe(
            this@CodeVerificationActivity,
            Observer {
                if ((it as CodeVerificationResult.Loading).show) {
                    this.dialogProgress.show()
                } else {
                    this.dialogProgress.dismiss()
                }
            }
        )
    }

    private fun setUpCodeButton() {
        codeButton.setOnClickListener {
            viewModel.verifyCode(codeVerification_et.text.toString())
        }
    }

}