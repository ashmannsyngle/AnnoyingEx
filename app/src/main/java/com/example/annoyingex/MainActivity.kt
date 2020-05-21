package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.annoyingex.managers.AENotificationManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aeapp = (application as AEApp)


        val letMeAnnoyYouManager = aeapp.letMeAnnoyYouManager


        intent.getStringExtra(AENotificationManager.NOTIFICATION_DATA)?.let {message ->
            tvMessage.text = "Text: $message"
        }

        letMeAnnoyYouManager.sheCrazyRefreshed()

        btnstart.setOnClickListener {
            letMeAnnoyYouManager.startAnnoyingEx()
        }

        btnstop.setOnClickListener {
            letMeAnnoyYouManager.stop()
        }
    }
}
