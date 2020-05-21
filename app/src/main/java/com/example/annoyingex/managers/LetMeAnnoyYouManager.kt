package com.example.annoyingex.managers

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class LetMeAnnoyYouManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingEx() {
        if (!isATWRRunning()) {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<SendATextWorker>(20, TimeUnit.MINUTES)
                .setInitialDelay(5000, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .addTag(ATWR_TAG)
                .build()
            workManager.enqueue(workRequest)
        }
    }

    fun sheCrazyRefreshed() {
        if (!isSCRRunning()) {
            val constraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<RefreshHerWorker>(2, TimeUnit.DAYS)
                .setConstraints(constraints)
                .addTag(SCR_TAG)
                .build()
            workManager.enqueue(workRequest)
        }
    }

    private fun isATWRRunning(): Boolean {
        return when(workManager.getWorkInfosByTag(ATWR_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    private fun isSCRRunning(): Boolean {
        return when(workManager.getWorkInfosByTag(SCR_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stop() {
        workManager.cancelAllWorkByTag(ATWR_TAG)
    }

    companion object {
        const val ATWR_TAG = "ATWR_TAG"
        const val SCR_TAG = "SCR_TAG"

    }
}