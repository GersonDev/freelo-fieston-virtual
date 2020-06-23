package com.spydevs.fiestonvirtual.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.util.extensions.openGalleryExternalApp
import com.spydevs.fiestonvirtual.util.extensions.openSettings
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.ui.main.camera.CameraActivity
import com.spydevs.fiestonvirtual.ui.main.photo.PhotoFragment
import com.spydevs.fiestonvirtual.ui.welcome.WelcomeDialogFragment
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_TO_MEDIA: Int = 2
    }

    private var galleryImageUri: Uri? = null
    private var showCustomReadPermissionDialog = false

    private var permissionsList = mutableListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getValuesFromIntent()
        setSupportActionBar(tlbGeneral)
        setUpViews()
        setUpViewListeners()
//        subscribeToGetCart()
//        shoppingCartViewModel.requestCart()
//        initBadge()

        showWelcomeScreen()
    }

    private fun setUpViews() {
        val navView: BottomNavigationView = findViewById(R.id.mainBottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    private fun setUpViewListeners() {
        cameraImageButton.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
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

    /**
     * Validate the necessary permission for writing and reading files from storage or external applications
     * since we can open an external camera or gallery application but we cannot read or write the pictures or files
     * in current application.
     */
    fun validatePermission(manifestPermission: String) {
        when (manifestPermission) {
            Manifest.permission.READ_EXTERNAL_STORAGE -> {
                if (ContextCompat.checkSelfPermission(this, manifestPermission)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    openGalleryExternalApp()
                } else {
                    if (showCustomReadPermissionDialog) {
                        showAppSettingsDialogFragment("Storage 1")
                    } else {
                        requestPermissions()
                    }
                }
            }
        }
    }

    private fun showAppSettingsDialogFragment(message: String) {
        setupAlertDialog(
            message = getString(R.string.open_settings_tap_permissions, message),
            onPositiveButtonClick = {
                openSettings()
            }
        )
    }

    /**
     * Request and show a permissions modal
     */
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            permissionsList.toTypedArray(),
            CameraActivity.REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_TO_MEDIA -> if (resultCode == Activity.RESULT_OK) {
                //data?.data is NOT NULL when selecting any file from gallery
                //mInstaCropper.setImageUri(data?.data!!)
                galleryImageUri = data?.data

                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                navHostFragment?.let {
                    it.childFragmentManager.fragments[0] as PhotoFragment
                }?.setImage(galleryImageUri)

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(
                    this,
                    getString(R.string.select_an_image_at_least),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
