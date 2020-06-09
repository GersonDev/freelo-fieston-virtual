package com.spydevs.fiestonvirtual.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.welcome.WelcomeDialogFragment
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //getValuesFromIntent()
        setSupportActionBar(tlbGeneral)
        //setupViews()
//        setupNavigation()
//        setupBadge()
//        setupViewListeners()
//        subscribeToGetCart()
//        init()
//        shoppingCartViewModel.requestCart()
//        initBadge()

        val navView: BottomNavigationView = findViewById(R.id.mainBottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        showWelcomeScreen()
    }

    private fun showWelcomeScreen() {
        //TODO delete data test.
        WelcomeDialogFragment.newInstance(
            "BIENVENIDO JUAN",
            "Hoy celebramos el aniversario 50 de Super Mercados metro"
            ,
            "Un viaje para dos personas a Cancun",
            "https://image.freepik.com/vector-gratis/caja-regalo-sorpresa-abierta_3446-340.jpg"
        ).show(supportFragmentManager, WelcomeDialogFragment.TAG)
    }
}
