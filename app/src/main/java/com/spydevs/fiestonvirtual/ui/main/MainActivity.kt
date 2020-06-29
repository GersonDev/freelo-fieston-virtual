package com.spydevs.fiestonvirtual.ui.main

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.ui.main.camera.CameraActivity
import com.spydevs.fiestonvirtual.ui.main.photo.PhotoFragment
import com.spydevs.fiestonvirtual.ui.main.welcome.WelcomeDialogFragment
import com.spydevs.fiestonvirtual.util.extensions.openGalleryExternalApp
import com.spydevs.fiestonvirtual.util.extensions.openSettings
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
import kotlinx.android.synthetic.main.toolbar_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()

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

        subscribeToWelcome()
        subscribeToAnyError()

        mainViewModel.getWelcome()
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

    private fun subscribeToWelcome() {
        mainViewModel.welcome.observe(this, Observer {
            showWelcomeScreen(it)
        })
    }

    private fun subscribeToAnyError() {
        mainViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun showWelcomeScreen(welcome: Welcome) {
        WelcomeDialogFragment.newInstance(
            welcome.title,
            welcome.description,
            welcome.subtitle,
            welcome.imageUrl
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

    //TODO WE WILL USE THIS FUNCTION LATER
    private fun getRealPathFromURI(contentURI: Uri): String? {
        val result: String?
        val cursor =
            contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }


    fun getPathFromUri(
        context: Context,
        uri: Uri
    ): String? {
        val isKitKat =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }

                // TODO handle non-primary volumes
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(
                    split[1]
                )
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {

            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
                context,
                uri,
                null,
                null
            )
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(
            column
        )
        try {
            cursor = context.contentResolver.query(
                uri!!, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null) cursor.close()
        }
        return null
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }
}
