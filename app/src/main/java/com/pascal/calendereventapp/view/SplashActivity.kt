package com.pascal.calendereventapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pascal.calendereventapp.R

class SplashActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}