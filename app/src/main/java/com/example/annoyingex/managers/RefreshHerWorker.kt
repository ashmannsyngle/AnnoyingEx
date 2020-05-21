package com.example.annoyingex.managers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.annoyingex.AEApp

class RefreshHerWorker(private val context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    override fun doWork(): Result {

        val aeapp = (context as AEApp)

        aeapp.fetchJSONManager.getMessages { result ->
            aeapp.listOfMessages = result.messages
        }

        return Result.success()
    }


}