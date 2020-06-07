package com.spydevs.fiestonvirtual.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spydevs.fiestonvirtual.R
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
    }
}
