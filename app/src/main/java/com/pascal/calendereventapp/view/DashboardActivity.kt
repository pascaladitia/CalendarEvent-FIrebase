package com.pascal.calendereventapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pascal.calendereventapp.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()

        initView()
    }

    private fun initView() {
        dashboard_start.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}