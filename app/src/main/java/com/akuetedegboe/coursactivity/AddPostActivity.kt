package com.akuetedegboe.coursactivity


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.akuetedegboe.coursactivity.data.Post
import com.akuetedegboe.coursactivity.db.Database
import java.io.ByteArrayOutputStream

class AddPostActivity : AppCompatActivity() {
    lateinit var editTitre: EditText
    lateinit var editDescription: EditText
    lateinit var imgAlter: ImageView
    lateinit var btnPost: Button
    var isEditMode = false
    var bitmap: Bitmap? = null
    lateinit var db: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        editTitre = findViewById(R.id.editTitre)
        editDescription = findViewById(R.id.editDescription)
        imgAlter = findViewById(R.id.imgAlter)
        btnPost = findViewById(R.id.btnPost)

        val mode = intent.getStringExtra("mode")
        val id = intent.getIntExtra("id", 0)
        val titre = intent.getStringExtra("titre")
        val description = intent.getStringExtra("description")
        val image = intent.getByteArrayExtra("image")

        db = Database(this)
        if (mode == "add") {
            isEditMode = false
        } else {
            isEditMode = true
            bitmap= image?.let { getBitmap(it)

            }
            imgAlter.setImageBitmap(bitmap)
        }

        editTitre.setText(titre)
        editDescription.setText(description)

        val galeryLuncher = registerForActivityResult(ActivityResultContracts.GetContent()) { data ->
                val inputStream = contentResolver.openInputStream(data!!)
                bitmap = BitmapFactory.decodeStream(inputStream)
                imgAlter.setImageBitmap(bitmap)
            }

        imgAlter.setOnClickListener {
            // ouvrir galerie
            galeryLuncher.launch("image/*")
        }

        btnPost.setOnClickListener {
            //Recuperer les differentes valeurs
            val titre = editTitre.text.toString()
            val description = editDescription.text.toString()
            if (titre.isEmpty() || description.isEmpty()||bitmap==null) {
                Toast.makeText(this, "Faut remplir tous les champs", Toast.LENGTH_LONG).show()
            } else {
                if (isEditMode) {
                    val imagesBLOB: ByteArray = getBytes(bitmap!!)

                    var updatePost = db.findPost(id)

                    updatePost.id = id
                    updatePost.titre = titre
                    updatePost.description = description
                    updatePost.image = imagesBLOB
                    db.updatePost(updatePost)

                    editTitre.setText("")
                    editDescription.setText("")
                    bitmap = null

                    finish()

                } else {
                    val imagesBLOB: ByteArray = getBytes(bitmap!!)

                    val post = Post(titre, description, imagesBLOB)

                    db.addPost(post)

                    editTitre.setText("")
                    editDescription.setText("")
                    bitmap = null

                    finish()
                }

            }
        }
    }

    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    fun getBitmap(byteArray: ByteArray): Bitmap {
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bitmap
    }
}