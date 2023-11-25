package com.akuetedegboe.coursactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class ToastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)

        val btn=findViewById<Button>(R.id.btnAct)
        val nom : String = "vlad"

        btn.setOnClickListener{view: View ->

            Toast.makeText(this,"bonjour $nom",Toast.LENGTH_LONG).show()

        }
    }
}