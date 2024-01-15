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
        val btnConnect = findViewById<Button>(R.id.connect)
        val btnToast = findViewById<Button>(R.id.btnToast)
        val email = findViewById<EditText>(R.id.evEmail)
        val password = findViewById<EditText>(R.id.evPass)

        btnConnect.setOnClickListener { view: View ->

            val txtMail = email.text.toString()
            val txtPassword = password.text.toString()

            if (txtMail.trim().isEmpty() || txtPassword.trim().isEmpty()) {

                Toast.makeText(this, "Les champs ne doivent pas être vide", Toast.LENGTH_LONG)
                    .show()
            } else {

                val correctEmail = "mobile"
                val correctPassword = "123"

                if (correctEmail == txtMail && correctPassword == txtPassword) {
                    email.setText("")
                    password.setText("")

                    //intent pour le list view
                    Intent(this, HomeActivity::class.java).also {
                        startActivity(it)
                    }



                } else {
                    Toast.makeText(this, "Email password n'est pas correct", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        btnToast.setOnClickListener {view: View ->
            val edtMail = email.text.toString()
            val edtPassword = password.text.toString()
            println("Mon texte saisie $edtMail")
            Intent(this, ToastActivity::class.java).also {
                it.putExtra("mail", edtMail)
                it.putExtra("pass", edtPassword)
                startActivity(it)
            }
        }
    }


}