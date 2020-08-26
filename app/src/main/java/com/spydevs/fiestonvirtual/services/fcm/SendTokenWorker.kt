package com.spydevs.fiestonvirtual.services.fcm

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.notifications.SendTokenFirebaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class SendTokenWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters), KoinComponent {

    private val sendTokenFirebaseUseCase: SendTokenFirebaseUseCase by inject()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        inputData.getString(TOKEN)?.let {
            when (sendTokenFirebaseUseCase(it)) {
                is ResultType.Success -> {
                    Result.success()
                }
                is ResultType.Error -> {
                    Result.failure()
                }
            }
        }
        Result.failure()
    }

    companion object {
        const val TOKEN = "token"
    }
}