package com.spydevs.fiestonvirtual.ui.main.photo

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import com.spydevs.fiestonvirtual.ui.main.photo.UploadFileCoroutineWorker.Companion.FILE_PATH_KEY
import com.spydevs.fiestonvirtual.ui.main.photo.UploadFileCoroutineWorker.Companion.TITLE_MESSAGE
import com.spydevs.fiestonvirtual.util.NativeGallery
import com.spydevs.fiestonvirtual.util.RealPathUtil
import com.spydevs.fiestonvirtual.util.extensions.*
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.frame_layout_upload_image_video.*
import java.util.concurrent.TimeUnit

class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private val TAG by lazy{
        PhotoFragment::class.java.simpleName
    }

    private lateinit var photoViewModel: PhotoViewModel
    private var filePathUri: String = ""

    private val dialogProgress by lazy {
        requireActivity().setupLoadingAlertDialog()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewListeners()
    }

    private fun setupViewListeners() {
        photo_btn_choose.setOnClickListener {
            (requireActivity() as MainActivity).validatePermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        photo_btn_publish.setOnClickListener {
            startWork()
        }
    }

    fun setImage(uri: Uri?) {
        uri?.let { noNullUri ->
            this.filePathUri = RealPathUtil.getRealPath(requireActivity(), noNullUri) ?: ""
            photoImageView.loadUrl(this.filePathUri)
            showEmptyView(this.filePathUri)
            showFileIdentifierText(NativeGallery.getMimeType(this.filePathUri))
            showUploadButton(this.filePathUri)
        }
    }

    private fun startWork() {
        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(UploadFileCoroutineWorker::class.java)
                .setInputData(createInputData(filePathUri, photoFragment_et_title.text.toString()))
                .setInitialDelay(DURATION_TIME_IN_SECONDS, TimeUnit.SECONDS).build()

        val workManager = WorkManager.getInstance(requireActivity())
        workManager.enqueue(oneTimeWorkRequest)

        dialogProgress.show()

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(requireActivity(), Observer {
                if (it?.state == null) return@Observer
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        dialogProgress.dismiss()
                        val successOutputData = it.outputData
                        requireActivity().setupAlertDialog(
                            message = successOutputData.getString(UploadFileCoroutineWorker.SUCCESS_KEY) ?: "Envío exitoso",
                            onPositiveButtonClick = {
                                navigateToGallery()
                            }
                        )
                    }
                    WorkInfo.State.FAILED -> {
                        dialogProgress.dismiss()
                        val failureOutputData = it.outputData
                        requireActivity().setupAlertDialog(
                            message = failureOutputData.getString(UploadFileCoroutineWorker.ERROR_KEY) ?: "Hubo un error en el servicio"
                        )
                    }
                }
            })
    }

    private fun createInputData(imagePath: String, title: String = ""): Data {
        return Data.Builder()
            .putString(FILE_PATH_KEY, imagePath)
            .putString(TITLE_MESSAGE, title)
            .build()
    }

    private fun showEmptyView(filePath: String) {
        if (filePath.isEmpty()) {
            upload_fl_imageAndVideo.visibility = View.INVISIBLE
            upload_ll_emptyView.visibility = View.VISIBLE
        } else {
            upload_fl_imageAndVideo.visibility = View.VISIBLE
            upload_ll_emptyView.visibility = View.INVISIBLE
        }
    }

    private fun showFileIdentifierText(fileMimeType: String?) {
        when (fileMimeType) {
            "video/mp4" -> {
                upload_tv_mimeType.text = "video"
            }
            "image/jpeg" -> {
                upload_tv_mimeType.text = "imagen"
            }
            else -> {
                Log.e(TAG, "Unknown file mime type")
            }
        }
    }

    private fun showUploadButton(filePathUri: String) {
        if(filePathUri.isEmpty()) {
            photo_btn_choose.visibility = View.VISIBLE
            photo_btn_publish.visibility = View.INVISIBLE
        } else {
            photo_btn_choose.visibility = View.INVISIBLE
            photo_btn_publish.visibility = View.VISIBLE
        }
    }

    private fun navigateToGallery() {
        val navController = findNavController()
        navController.navigate(R.id.action_navigation_photos_to_navigation_gallery)
    }

    companion object {
        const val DURATION_TIME_IN_SECONDS: Long = 2
    }

}
