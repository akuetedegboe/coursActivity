package com.akuetedegboe.coursactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnConnect=findViewById<Button>(R.id.connect)

        btnConnect.setOnClickListener({view: View ->
           val myIntent :Intent=Intent(this,HomeActivity::class.java)
            startActivity(myIntent)
        })
    }
}