package com.akuetedegboe.coursactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ToastActivity : AppCompatActivity() {

    lateinit var etMail: EditText
    lateinit var etPass: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
        etMail = findViewById(R.id.editUsername)
        etPass = findViewById(R.id.editPass)

        val mail = intent.getStringExtra("mail")
        val pass = intent.getStringExtra("pass")

        println("Mon texte recupéré $mail")
        etMail.setText(mail)
        etPass.setText(pass)


        val btn = findViewById<Button>(R.id.btnAct)
        val nom: String = "vlad"

        btn.setOnClickListener { view: View ->

            Toast.makeText(this, "bonjour $nom", Toast.LENGTH_LONG).show()

        }
    }
}