package com.pascal.calendereventapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pascal.calendereventapp.R
import com.pascal.calendereventapp.model.Kehadiran
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    private var db: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        supportActionBar?.setTitle("Input Kehadiran");

        initView()
    }

    private fun initView() {
        db = FirebaseDatabase.getInstance()
        myRef = db?.getReference("kalender")

        input_hadir.setOnClickListener {
            updateHadir()
        }

        input_alpa.setOnClickListener {
            updateAlpa()
        }

        input_sakit.setOnClickListener {
            updateSakit()
        }

        input_Cancel.setOnClickListener {
            onBackPressed()
        }
    }

    private fun updateHadir() {
        val tanggal = input_tanggal.text.toString()
        val tanggal2 = input_tanggal2.text.toString()

        if (tanggal.isNotEmpty() && tanggal2.isNotEmpty()) {
            if (tanggal.equals(tanggal2)) {
                showToast("Tanggal tidak boleh sama")
            } else {
                val event = Kehadiran(tanggal, tanggal2, "Hadir", "#FF00FF00")
                val key = myRef?.push()?.key
                myRef?.child(key ?: "")?.setValue(event)
                finish()
                showToast("Siswa Hadir")
            }
        } else {
            showToast("Tanggal harus diisi")
        }
    }

    private fun updateAlpa() {
        val tanggal = input_tanggal.text.toString()
        val tanggal2 = input_tanggal2.text.toString()

        if (tanggal.isNotEmpty() && tanggal2.isNotEmpty()) {
            if (tanggal.equals(tanggal2)) {
                showToast("Tanggal tidak boleh sama")
            } else {
                val event = Kehadiran(tanggal, tanggal2, "Alpa", "#FFFF0000")
                val key = myRef?.push()?.key
                myRef?.child(key ?: "")?.setValue(event)
                finish()
                showToast("Siswa Alpa")
            }
        } else {
            showToast("Tanggal harus diisi")
        }
    }

    private fun updateSakit() {
        val tanggal = input_tanggal.text.toString()
        val tanggal2 = input_tanggal2.text.toString()

        if (tanggal.isNotEmpty() && tanggal2.isNotEmpty()) {
            if (tanggal.equals(tanggal2)) {
                showToast("Tanggal tidak boleh sama")
            } else {
                val event = Kehadiran(tanggal, tanggal2, "Sakit", "#FFFFFF00")
                val key = myRef?.push()?.key
                myRef?.child(key ?: "")?.setValue(event)
                finish()
                showToast("Siswa Sakit")
            }
        } else {
            showToast("Tanggal harus diisi")
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}