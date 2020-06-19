package com.spydevs.fiestonvirtual.ui.main.camera

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.framework.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.util.ImagesUtil
import com.spydevs.fiestonvirtual.util.NativeGallery
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.content_camera.*
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE: Int = 1

        const val REQUEST_TAKE_PHOTO: Int = 1
        const val REQUEST_TO_MEDIA: Int = 2

        const val INDEX_WRITE_PERMISSION = 0
        const val INDEX_READ_PERMISSION = 1
    }

    private var currentPhotoPath: String = ""

    private var fileeee : File? = null

    private var galleryImageUri: Uri? = null
    private var showCustomWritePermissionDialog = false
    private var showCustomReadPermissionDialog = false

    private var permissionsList = mutableListOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        setSupportActionBar(findViewById(R.id.toolbar))

        setUpViewListeners()
    }

    private fun setUpViewListeners() {
        fabCamera.setOnClickListener {
            validatePermission(permissionsList[INDEX_WRITE_PERMISSION])
        }

        fabGallery.setOnClickListener {
            validatePermission(permissionsList[INDEX_READ_PERMISSION])
        }

        fabNextActivity.setOnClickListener {
            if (galleryImageUri != null) {
                //startMaterialImageCropperActivity(galleryImageUri!!)
            } else {
                Toast.makeText(this, "Select a Picture from Gallery", Toast.LENGTH_SHORT).show()
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
                    if (showCustomReadPermissionDialog) {
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
                    openGalleryExternalApp()
                } else {
                    if (showCustomWritePermissionDialog) {
                        showAppSettingsDialogFragment("Storage 1")
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

                        Manifest.permission.READ_EXTERNAL_STORAGE -> {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                openGalleryExternalApp()
                            } else {
                                // shouldShowRequestPermissionRationale return false if the user check "Don't ask again" or "Permission disabled"
                                // for more information https://youtu.be/C8lUdPVSzDk?t=2m23s
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                                        this,
                                        permissions[i]
                                    )
                                ) {
                                    showCustomReadPermissionDialog = true
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

    /**
     * Show custom dialog if user checks 'Don't ask again'
     */
    private fun openGalleryExternalApp() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQUEST_TO_MEDIA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_TO_MEDIA -> if (resultCode == Activity.RESULT_OK) {
                //data?.data is NOT NULL when selecting any file from gallery
                //mInstaCropper.setImageUri(data?.data!!)
                Log.e("Gallery Image Uri", "data ${data?.data}")
                galleryImageUri = data?.data
                imgPhoto.setImageURI(galleryImageUri)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(
                    this,
                    getString(R.string.select_an_image_at_least),
                    Toast.LENGTH_SHORT
                ).show()
            }

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
            galleryAddPic()
        }
    }

    private fun showAppSettingsDialogFragment(message: String) {
        setupAlertDialog(
            message = getString(R.string.open_settings_tap_permissions, message),
            onPositiveButtonClick = {
                openAppSettings()
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

                fileeee = photoFile
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

    private fun openAppSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    private fun galleryAddPic() {

        NativeGallery.SaveMedia(this, 0, currentPhotoPath, "Camera")


    }

    private fun galleryAddPic2(filename: String) {



        if (Build.VERSION.SDK_INT >= 29) {
            val values = ContentValues()
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/Camera")
            values.put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis())
            values.put(MediaStore.MediaColumns.IS_PENDING, true)
            val uri: Uri? =
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                try {
                    if (WriteFileToStream(
                            fileeee!!,
                            contentResolver.openOutputStream(uri)!!
                        )
                    ) {
                        values.put(MediaStore.MediaColumns.IS_PENDING, false)
                        getContentResolver().update(uri, values, null, null)
                    }
                } catch (e: java.lang.Exception) {
                    Log.e("Unity", "Exception:", e)
                    getContentResolver().delete(uri, null, null)
                }
            }
        }

    }

    private fun WriteFileToStream(file: File, out: OutputStream): Boolean {
        try {
            val `in`: InputStream = FileInputStream(file)
            try {
                val buf = ByteArray(1024)
                var len: Int
                while (`in`.read(buf).also { len = it } > 0) out.write(buf, 0, len)
            } finally {
                try {
                    `in`.close()
                } catch (e: java.lang.Exception) {
                    Log.e("Unity", "Exception:", e)
                }
            }
        } catch (e: java.lang.Exception) {
            Log.e("Unity", "Exception:", e)
            return false
        } finally {
            try {
                out.close()
            } catch (e: java.lang.Exception) {
                Log.e("Unity", "Exception:", e)
            }
        }
        return true
    }
}