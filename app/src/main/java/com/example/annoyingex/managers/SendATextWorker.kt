package com.example.annoyingex.managers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.annoyingex.AEApp
import kotlin.random.Random

class SendATextWorker(private val context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    private var listOfMessages: List<String> = (context as AEApp).listOfMessages

    override fun doWork(): Result {

        if (listOfMessages.isEmpty()) {
            (context as AEApp).aeNotificationManager.postNotif("unable to retrieve message", 0)
        } else {
            val index = Random.nextInt(0, listOfMessages.size - 1)
            val message = listOfMessages[index]

            (context as AEApp).aeNotificationManager.postNotif(message, index)
        }

        return Result.success()
    }


}