package com.pascal.calendereventapp.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.chintanpatel.materialeventcalendar.CalenderView
import com.chintanpatel.materialeventcalendar.EventItem
import com.google.firebase.database.*
import com.pascal.calendereventapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_detail.*

class MainActivity : AppCompatActivity() {

    private var db: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        db = FirebaseDatabase.getInstance()
        myRef = db?.getReference("kalender")
        var dataEvent: ArrayList<EventItem> = arrayListOf()

        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (datas in snapshot.children) {
                    val start = datas.child("start").value.toString()
                    val end = datas.child("end").value.toString()
                    val kehadiran = datas.child("kehadiran").value.toString()
                    val color = datas.child("color").value.toString()
                    val id = datas.key

                    dataEvent.add(EventItem(start, end, kehadiran, color, id.toString()))
                    eventCalendar.addEventList(dataEvent)

                    showEvent()
                    eventCalendar.eventClickListener
                }
            }
        })

        main_add.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showEvent() {
        eventCalendar.setCalenderEventClickListener(object : CalenderView.CalenderEventClickListener {
            override fun onEventClick(eventItem: EventItem) {
                //put your logic on event click
                dialogView(eventItem)
            }
        })
    }

    private fun dialogView(eventItem: EventItem?) {
        Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(R.layout.dialog_detail)

            dialog_kehadiran.text = "${eventItem?.title}"
            dialog_tanggal.text = "Tanggal : ${eventItem?.start}"

            dialog_Cancel.setOnClickListener {
                this?.dismiss()
            }
        }.show()
    }
}