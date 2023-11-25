package com.akuetedegboe.coursactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnConnect=findViewById<Button>(R.id.connect)
        val btnToast=findViewById<Button>(R.id.btnToast)

        btnConnect.setOnClickListener { view: View ->
                    val myIntent: Intent = Intent(this, HomeActivity::class.java)
                    startActivity(myIntent)
        }

        btnToast.setOnClickListener{view:View->
            Intent(this,ToastActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}