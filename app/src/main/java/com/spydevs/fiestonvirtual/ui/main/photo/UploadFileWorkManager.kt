package com.spydevs.fiestonvirtual.ui.main.photo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

//TODO IMPROVE THIS CODE BY USING COROUTINEWORKS INSTEAD OF WORKER
class UploadFileWorkManager(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        val imagePath = inputData.getString("imagePath")
        //Defining Retrofit Api service
        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val file = File(imagePath!!)
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val fileUploadMultiPart = MultipartBody.Part.createFormData("file", file.name, requestBody)

        val fileName = RequestBody.create(MediaType.parse("text/plain"), file.name)
        val apiService = retrofit.create(FiestonVirtualApi::class.java)

        return when (val uploadImageResponse = apiService.uploadFile(fileUploadMultiPart, fileName)) {
            is NetworkResponse.Success -> {
                showNotification(CHANNEL_NAME, "Image uploaded successfully")
                Result.success()
            }
            is NetworkResponse.ApiError -> {
                print(uploadImageResponse.body)
                Result.failure()
            }
            is NetworkResponse.NetworkError -> {
                print(uploadImageResponse.error.message)
                Result.failure()
            }
            is NetworkResponse.UnknownError -> {
                print(uploadImageResponse.error.message)
                Result.failure()
            }
        }
    }

    private fun showNotification(title: String, task: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_NAME,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notification =
            NotificationCompat.Builder(applicationContext, CHANNEL_NAME)
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher_round)
        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_NAME = "inducesmile"
    }
}