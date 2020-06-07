package com.spydevs.fiestonvirtual.ui.code_verification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import com.spydevs.fiestonvirtual.ui.welcome.WelcomeDialogFragment
import kotlinx.android.synthetic.main.activity_code_verification.*

class CodeVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)
        setupViewListeners()
        //TODO delete data test.
        WelcomeDialogFragment.newInstance(
            "BIENVENIDO JUAN",
            "Hoy celebramos el aniversario 50 de Super Mercados metro"
            ,
            "Un viaje para dos personas a Cancun",
            "https://image.freepik.com/vector-gratis/caja-regalo-sorpresa-abierta_3446-340.jpg"
        ).show(supportFragmentManager, WelcomeDialogFragment.TAG)
    }

    fun setupViewListeners() {
        codeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
