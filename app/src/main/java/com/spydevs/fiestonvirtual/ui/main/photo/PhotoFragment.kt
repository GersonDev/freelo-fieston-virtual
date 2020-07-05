package com.spydevs.fiestonvirtual.ui.main.photo

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import com.spydevs.fiestonvirtual.util.RealPathUtil
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import kotlinx.android.synthetic.main.fragment_photo.*
import java.util.concurrent.TimeUnit

class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private lateinit var photoViewModel: PhotoViewModel
    private var imagePathUri: String = ""

    private val dialogProgress by lazy {
        requireActivity().setupLoadingAlertDialog()
    }

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
            //TODO UNCOMMENT FOR THE NEXT RELEASE
            //startWork()
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

        val workManager = WorkManager.getInstance(requireActivity())
        workManager.enqueue(oneTimeWorkRequest)

        dialogProgress.show()

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(requireActivity(), Observer {

            if (it?.state == null)
                return@Observer
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val successOutputData = it.outputData
                    Toast.makeText(requireActivity(), successOutputData.getString("KEY_SUCCESS"), Toast.LENGTH_SHORT).show()
                    dialogProgress.dismiss()
                }
                WorkInfo.State.FAILED -> {
                    val failureOutputData = it.outputData
                    Toast.makeText(requireActivity(), failureOutputData.getString("KEY_ERROR"), Toast.LENGTH_SHORT).show()
                    dialogProgress.dismiss()
                }
            }
        })
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
