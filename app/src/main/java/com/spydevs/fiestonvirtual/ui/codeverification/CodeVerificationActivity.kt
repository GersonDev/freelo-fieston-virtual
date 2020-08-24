package com.spydevs.fiestonvirtual.ui.codeverification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.iid.FirebaseInstanceId
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.services.fcm.SendTokenWorker
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
        subscribeToVerifySession()
        viewModel.verifySession()
    }

    private fun subscribeToSuccessCode() {
        viewModel.isSuccessCode.observe(
            this@CodeVerificationActivity,
            Observer {
                sendRegistrationToServer()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        )
    }

    //TODO send to MainActivity
    private fun sendRegistrationToServer() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
                val oneTimeWorkRequest = OneTimeWorkRequest
                    .Builder(SendTokenWorker::class.java)
                    .setInputData(
                        Data.Builder()
                            .putString(SendTokenWorker.TOKEN, it.result?.token)
                            .build()
                    )
                    .build()
                WorkManager
                    .getInstance(applicationContext)
                    .beginWith(oneTimeWorkRequest)
                    .enqueue()
            }
    }

    private fun subscribeToVerifySession() {
        viewModel.verificationSession.observe(
            this,
            Observer {
                when (val result = it as CodeVerificationResult.VerificationSession) {
                    is CodeVerificationResult.VerificationSession.Success -> {
                        if (result.inSession) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                    is CodeVerificationResult.VerificationSession.Loading -> {
                        if (result.show) {
                            dialogProgress.show()
                        } else {
                            dialogProgress.dismiss()
                        }
                    }
                    is CodeVerificationResult.VerificationSession.Error -> {
                        this.setupAlertDialog(
                            title = result.errorResponse.title,
                            message = result.errorResponse.message
                        )
                    }
                }
            }
        )
    }

    private fun subscribeToError() {
        viewModel.error.observe(
            this@CodeVerificationActivity,
            Observer {
                (it as CodeVerificationResult.CodeVerificationError).errorResponse.let { errorResponse ->
                    this.setupAlertDialog(
                        title = errorResponse.title,
                        message = errorResponse.message
                    )
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