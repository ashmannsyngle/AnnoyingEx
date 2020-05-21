package com.example.annoyingex

import android.app.Application
import com.example.annoyingex.managers.AENotificationManager
import com.example.annoyingex.managers.FetchJsonManager
import com.example.annoyingex.managers.LetMeAnnoyYouManager

class AEApp: Application() {

    lateinit var fetchJSONManager: FetchJsonManager
        private set

    lateinit var letMeAnnoyYouManager: LetMeAnnoyYouManager
        private set

    lateinit var aeNotificationManager: AENotificationManager
        private set

    var listOfMessages =  listOf<String>()

    override fun onCreate() {
        super.onCreate()

        fetchJSONManager = FetchJsonManager(this)
        letMeAnnoyYouManager = LetMeAnnoyYouManager(this)
        aeNotificationManager = AENotificationManager(this)
    }
}