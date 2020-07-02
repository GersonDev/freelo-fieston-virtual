package com.spydevs.fiestonvirtual.ui.main.photo

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import com.spydevs.fiestonvirtual.util.RealPathUtil
import kotlinx.android.synthetic.main.fragment_photo.*
import java.util.concurrent.TimeUnit

class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private lateinit var photoViewModel: PhotoViewModel
    private var imagePathUri: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewListeners()
    }

    private fun setupViewListeners() {
        photoButton.setOnClickListener {
            (requireActivity() as MainActivity).validatePermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        uploadFileButton.setOnClickListener {
            startWork()
        }
    }

    fun setImage(uri: Uri?) {
        uri?.let { noNullUri ->
            this.imagePathUri = RealPathUtil.getRealPath(requireActivity(), noNullUri) ?: ""
        }
        photoImageView.setImageURI(uri)
    }

    private fun startWork() {
        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(UploadFileCoroutineWorker::class.java)
                .setInputData(createInputData(imagePathUri))
                .setInitialDelay(DURATION_TIME_IN_SECONDS, TimeUnit.SECONDS).build()
        WorkManager.getInstance(requireActivity()).enqueue(oneTimeWorkRequest)
    }

    private fun createInputData(imagePath: String): Data {
        return Data.Builder()
            .putString("imagePath", imagePath)
            .build()
    }

    companion object {
        const val DURATION_TIME_IN_SECONDS: Long = 2
    }

}
