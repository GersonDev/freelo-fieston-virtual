package com.spydevs.fiestonvirtual.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.ui.main.home.HomeFragment
import com.spydevs.fiestonvirtual.ui.main.photo.PhotoFragment
import com.spydevs.fiestonvirtual.ui.main.photo.UploadFileCoroutineWorker
import com.spydevs.fiestonvirtual.ui.main.welcome.WelcomeDialogFragment
import com.spydevs.fiestonvirtual.util.ImagesUtil
import com.spydevs.fiestonvirtual.util.NativeGallery
import com.spydevs.fiestonvirtual.util.extensions.*
import kotlinx.android.synthetic.main.toolbar_main.*
import org.koin.android.ext.android.inject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()

    private val dialogProgress by lazy {
        setupLoadingAlertDialog()
    }

    private var rotatedBitmap: Bitmap? = null

    companion object {
        const val REQUEST_TO_MEDIA: Int = 2
        const val REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE: Int = 1
        const val REQUEST_TAKE_PHOTO: Int = 1
        const val INDEX_WRITE_PERMISSION = 0
    }


    private var currentPhotoPath: String = ""
    private var showCustomWritePermissionDialog = false


    private var galleryImageUri: Uri? = null
    private var showCustomReadPermissionDialog = false

    private var permissionsList = mutableListOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
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
            validatePermission(permissionsList[INDEX_WRITE_PERMISSION])
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
    fun validatePermission(manifestPermission: String, openOnlyImages: Boolean = false) {
        when (manifestPermission) {
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                if (ContextCompat.checkSelfPermission(this, manifestPermission)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    dispatchTakePictureIntent()
                } else {
                    if (showCustomWritePermissionDialog) {
                        showAppSettingsDialogFragment("Storage 0")
                    } else {
                        requestPermissions()
                    }
                }
            }
            Manifest.permission.READ_EXTERNAL_STORAGE -> {
                if (ContextCompat.checkSelfPermission(this, manifestPermission)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    if(openOnlyImages) {
                        openImageGalleryExternalApp()
                    } else {
                        openImageAndVideoGalleryExternalApp()
                    }
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

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Toast.makeText(this, "Error: ${ex.message}", Toast.LENGTH_SHORT).show()
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        applicationContext,
                        "${applicationContext.packageName}.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
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
            REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_TAKE_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                //Save the full-size photo
                setPic()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.select_an_image_at_least),
                    Toast.LENGTH_SHORT
                ).show()
            }
            REQUEST_TO_MEDIA -> if (resultCode == Activity.RESULT_OK) {
                //data?.data is NOT NULL when selecting any file from gallery
                //mInstaCropper.setImageUri(data?.data!!)
                galleryImageUri = data?.data

                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                navHostFragment?.let {
                    print(it.childFragmentManager.fragments[0])
                    when (val currentFragment = it.childFragmentManager.fragments[0]) {
                        is HomeFragment -> {
                            currentFragment.setImage(galleryImageUri)
                        }
                        is PhotoFragment -> {
                            currentFragment.setImage(galleryImageUri)
                        }
                    }
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(
                    this,
                    getString(R.string.select_an_image_at_least),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //Decode a scaled image
    private fun setPic() {
        //TODO 900 IS A HARDCODED VALUE. THE BEST OPTION IS TO GET WIDTH AND HEIGHT OF AN IMAGEVIEW IN YOUR XML
        //TODO HOWEVER THE CUSTOMER REQUIREMENTS IS TO LAUNCH THE CAMERA DIRECTLY WITHOUT AN IMAGEVIEW TO SHOW THE IMAGE.

        // Get the dimensions of the View
        val targetW: Int = 900
        val targetH: Int = 900

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            val imageOrientation = ImagesUtil.getImageAngleRotation(currentPhotoPath)
            val rotatedBitmap = ImagesUtil.getRotatedBitmap(bitmap, imageOrientation)
            this.rotatedBitmap = rotatedBitmap
            galleryAddPic()
            uploadCameraPhoto(this.rotatedBitmap)
        }
    }

    private fun galleryAddPic() {
        NativeGallery.saveMedia(this, 0, currentPhotoPath, "Camera")
    }

    private fun uploadCameraPhoto(rotatedBitmap: Bitmap?) {
        rotatedBitmap?.let {
            startWork()
        }
    }

    private fun startWork() {
        // Create the Constraints
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(UploadFileCoroutineWorker::class.java)
                .setInputData(createInputData(currentPhotoPath))
                .setConstraints(constraints)
                .setInitialDelay(PhotoFragment.DURATION_TIME_IN_SECONDS, TimeUnit.SECONDS).build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(oneTimeWorkRequest)

        dialogProgress.show()

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this, Observer {

            if (it?.state == null)
                return@Observer
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val successOutputData = it.outputData
                    Toast.makeText(
                        this,
                        successOutputData.getString("KEY_SUCCESS"),
                        Toast.LENGTH_SHORT
                    ).show()
                    dialogProgress.dismiss()
                }
                WorkInfo.State.FAILED -> {
                    val failureOutputData = it.outputData
                    Toast.makeText(
                        this,
                        failureOutputData.getString("KEY_ERROR"),
                        Toast.LENGTH_SHORT
                    ).show()
                    dialogProgress.dismiss()
                }
            }
        })
    }

    private fun createInputData(imagePath: String): Data {
        return Data.Builder()
            .putString(UploadFileCoroutineWorker.FILE_PATH_KEY, imagePath)
            .build()
    }

}
