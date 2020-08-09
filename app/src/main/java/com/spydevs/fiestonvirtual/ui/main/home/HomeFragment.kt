package com.spydevs.fiestonvirtual.ui.main.home

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import com.spydevs.fiestonvirtual.ui.main.photo.PhotoFragment
import com.spydevs.fiestonvirtual.ui.main.photo.UploadUserProfileImageCoroutineWorker
import com.spydevs.fiestonvirtual.util.RealPathUtil
import com.spydevs.fiestonvirtual.util.extensions.loadUrlWithCircularCrop
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_user.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by inject()

    private var filePathUri: String = ""

    private val dialogProgress by lazy {
        requireActivity().setupLoadingAlertDialog()
    }

    //TODO Remove Log for next tasks
    private val categoryAdapter by lazy {
        CategoryAdapter {
            when (it.name) {
                CATEGORY_NAME_PHOTO -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_gallery)
                }
                CATEGORY_NAME_CHAT -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_chat)
                }
                CATEGORY_NAME_TRIVIA -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_navigation_home_to_navigation_trivia)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToCategories()
        subscribeToUser()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupViewListeners()

        homeViewModel.getUsers()
    }

    private fun subscribeToCategories() {
        homeViewModel.categories.observe(viewLifecycleOwner, Observer {
            categoryAdapter.clearAllData()
            categoryAdapter.addData(it)
        })
    }

    private fun subscribeToUser() {
        homeViewModel.userLiveData.observe(viewLifecycleOwner, Observer { user ->
            nameTextView.text = user.name.plus(" ").plus(user.lastName)
            textView4.text = user.totalScore.toString()
            textView6.text = user.ranking.toString()
        })
    }

    private fun setupViews() {
        (categoryRecyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = 2
        categoryRecyclerView.adapter = categoryAdapter
    }

    private fun setupViewListeners() {
        homeFragment_iv_userProfile.setOnClickListener {
            (requireActivity() as MainActivity).validatePermission(Manifest.permission.READ_EXTERNAL_STORAGE, true)
        }
    }

    fun setImage(uri: Uri?) {
        uri?.let { noNullUri ->
            val realPath = RealPathUtil.getRealPath(requireActivity(), noNullUri)
            if (!realPath.isNullOrEmpty()) {
                this.filePathUri = realPath
                startWork()
            }
        }
    }

    private fun startWork() {
        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(UploadUserProfileImageCoroutineWorker::class.java)
                .setInputData(createInputData(filePathUri))
                .setInitialDelay(PhotoFragment.DURATION_TIME_IN_SECONDS, TimeUnit.SECONDS).build()

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
                        successOutputData.getString(
                            UploadUserProfileImageCoroutineWorker.SUCCESS_KEY
                        )?.let { imageUrl ->
                            homeFragment_iv_userProfile.loadUrlWithCircularCrop(imageUrl)
                        }
                    }
                    WorkInfo.State.FAILED -> {
                        dialogProgress.dismiss()
                        val failureOutputData = it.outputData
                        requireActivity().setupAlertDialog(
                            message = failureOutputData.getString(
                                UploadUserProfileImageCoroutineWorker.ERROR_KEY
                            ) ?: "Hubo un error en el servicio"
                        )
                    }
                }
            })
    }

    private fun createInputData(imagePath: String): Data {
        return Data.Builder()
            .putString(UploadUserProfileImageCoroutineWorker.FILE_PATH_KEY, imagePath)
            .build()
    }

    companion object {
        const val CATEGORY_NAME_PHOTO = "FOTOS"
        const val CATEGORY_NAME_CHAT = "CHAT"
        const val CATEGORY_NAME_TRIVIA = "TRIVIAS"
        const val CATEGORY_NAME_PLAY_LIST = "PLAY LIST"
    }

}
