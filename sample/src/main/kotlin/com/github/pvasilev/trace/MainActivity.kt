package com.github.pvasilev.trace

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            clickListener()
        }
    }


    @Trace("clickListener")
    fun clickListener() {
        Thread.sleep(1_000)
    }
}