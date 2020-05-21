package com.example.annoyingex.managers

import com.example.annoyingex.model.Message
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class FetchJsonManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)
    var listOfMessages: List<String>? = null
    private val ctx: Context = context

    fun getMessages(onMessageReady: (Message) -> Unit) {
        val messagesURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val request = StringRequest(Request.Method.GET, messagesURL, { response ->
            val gson = Gson()
            val response = gson.fromJson(response, Message::class.java)
            listOfMessages = response.messages
            onMessageReady(response)
        }, {
            listOfMessages = listOf<String>("unable to retrieve message")
        })
        queue.add(request)
    }

}