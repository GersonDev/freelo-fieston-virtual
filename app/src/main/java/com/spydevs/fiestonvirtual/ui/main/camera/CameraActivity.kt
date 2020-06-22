package com.spydevs.fiestonvirtual.ui.main.camera

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
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.framework.extensions.openSettings
import com.spydevs.fiestonvirtual.framework.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.util.ImagesUtil
import com.spydevs.fiestonvirtual.util.NativeGallery
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.content_camera.*
import org.koin.android.ext.android.inject
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {

    private val cameraViewModel: CameraViewModel by inject()

    private var rotatedBitmap: Bitmap? = null

    companion object {
        const val REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE: Int = 1
        const val REQUEST_TAKE_PHOTO: Int = 1
        const val INDEX_WRITE_PERMISSION = 0
    }

    private var currentPhotoPath: String = ""
    private var showCustomWritePermissionDialog = false

    private var permissionsList = mutableListOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        setSupportActionBar(toolbar)

        setUpView()
        setUpViewListeners()
    }

    private fun setUpView() {
        fabUploadCameraPhoto.isEnabled = false
    }

    private fun setUpViewListeners() {
        toolbar.setNavigationOnClickListener {
            finish()
        }

        fabCamera.setOnClickListener {
            validatePermission(permissionsList[INDEX_WRITE_PERMISSION])
        }

        fabUploadCameraPhoto.setOnClickListener {
            rotatedBitmap?.let {
                cameraViewModel.uploadImage(it)
            }
        }
    }

    /**
     * Validate the necessary permission for writing and reading files from storage or external applications
     * since we can open an external camera or gallery application but we cannot read or write the pictures or files
     * in current application.
     */
    private fun validatePermission(manifestPermission: String) {
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
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE -> {
                for (i in permissions.indices) {
                    when (permissions[i]) {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                dispatchTakePictureIntent()
                            } else {
                                // shouldShowRequestPermissionRationale return false if the user check "Don't ask again" or "Permission disabled"
                                // for more information https://youtu.be/C8lUdPVSzDk?t=2m23s
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                                        this,
                                        permissions[i]
                                    )
                                ) {
                                    showCustomWritePermissionDialog = true
                                }
                            }
                        }
                    }
                }
            }
        }
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

                //Get the thumbnail
//                val imageBitmap = data?.extras?.get("data") as Bitmap
//                imgPhoto.setImageBitmap(imageBitmap)

                //Save the full-size photo
                setPic()

            } else {
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
        // Get the dimensions of the View
        val targetW: Int = imgPhoto.width
        val targetH: Int = imgPhoto.height

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
            imgPhoto.setImageBitmap(rotatedBitmap)
            this.rotatedBitmap = rotatedBitmap
            galleryAddPic()
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

    private fun galleryAddPic() {
        NativeGallery.saveMedia(this, 0, currentPhotoPath, "Camera")
    }

}