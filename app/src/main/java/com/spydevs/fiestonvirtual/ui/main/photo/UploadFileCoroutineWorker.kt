package com.spydevs.fiestonvirtual.ui.main.photo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.File

class UploadFileCoroutineWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters), KoinComponent {

    private val fiestonVirtualApi: FiestonVirtualApi by inject()
    private val usersRepository: UsersRepository by inject()

    private var notificationManager: NotificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val imagePath = inputData.getString("imagePath")
        if (imagePath == null) Result.failure()

        val file = File(imagePath)
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val fileUploadMultiPart = MultipartBody.Part.createFormData("file", file.name, requestBody)
        val fileName = RequestBody.create(MediaType.parse("text/plain"), file.name)

        val progress = "Starting Uploading..."
        setForeground(createForegroundInfo(CHANNEL_NAME, "Image uploaded successfully", progress))

        val user = usersRepository.getLocalUser()

        when (val uploadImageResponse =
            fiestonVirtualApi.uploadFile(fileUploadMultiPart, user.id, user.idEvent, 1)) {
            is NetworkResponse.Success -> {
                val data = workDataOf(
                    "KEY_SUCCESS" to "RESPUESTA EXITOSA"
                )
                notificationManager.notify(2, createSimpleNotification("Image",
                uploadImageResponse.body.message))


                Result.success(data)
            }
            is NetworkResponse.ApiError -> {
                val data = workDataOf(
                    "KEY_ERROR" to uploadImageResponse.body
                )
                Result.failure(data)
            }
            is NetworkResponse.NetworkError -> {
                val data = workDataOf(
                    "KEY_ERROR" to uploadImageResponse.error.message
                )
                Result.failure(data)
            }
            is NetworkResponse.UnknownError -> {
                val data = workDataOf(
                    "KEY_ERROR" to uploadImageResponse.error.message
                )
                Result.failure(data)
            }
        }
    }

    private fun download(inputUrl: String, outputFile: String) {
        // Downloads a file and updates bytes read
        // Calls setForegroundInfoAsync() periodically when it needs to update
        // the ongoing Notification

    }

    private fun createForegroundInfo(
        title: String,
        task: String,
        progress: String
    ): ForegroundInfo {
        // Build a notification using bytesRead and contentLength
        val context = applicationContext
        val id = context.getString(R.string.notification_channel_id)
        val title = context.getString(R.string.notification_title)
        val cancel = context.getString(R.string.cancel_download)
        // This PendingIntent can be used to cancel the worker
        val intent = WorkManager.getInstance(context)
            .createCancelPendingIntent(getId())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
        val notification = createNotification(title, task, progress, cancel, intent)
        return ForegroundInfo(NOTIFICATION_ID, notification)
    }

    private fun createNotification(
        title: String,
        task: String,
        progress: String,
        cancel: String,
        intent: PendingIntent
    ): Notification {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(createChannel())
        }
        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(title)
            .setTicker(title)
            .setContentText(progress)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_delete, cancel, intent)
            .build()
    }

    private fun createSimpleNotification(
        title: String,
        description: String
    ): Notification {

        val context = applicationContext

        val intent = WorkManager.getInstance(context)
            .createCancelPendingIntent(getId())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(createChannel())
        }

        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .addAction(android.R.drawable.ic_delete, "cancel", intent)
            .build()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(): NotificationChannel {
        // Create a Notification channel
        return NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
    }

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "inducesmile"
        const val CHANNEL_NAME = "carlos"
    }
}