package com.akuetedegboe.coursactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val listPosts = findViewById<ListView>(R.id.list_posts)


        val postArray = arrayListOf(
            Post(
                "Livre 1",
                "Voici la description d'un bon Livre de la fable de la fontaine du Lycée de France ",
                R.drawable.image1
            ),
            Post(
                "Livre 2",
                "Voici la description d'un bon Livre de la fable de la fontaine du Lycée de France ",
                R.drawable.image2
            ),
            Post(
                "Livre 3",
                "Voici la description d'un bon Livre de la fable de la fontaine du Lycée de France ",
                R.drawable.image3
            ),
            Post(
                "Livre 4",
                "Voici la description d'un bon Livre de la fable de la fontaine du Lycée de France ",
                R.drawable.image4
            ),
            Post(
                "Livre 5",
                "Voici la description d'un bon Livre de la fable de la fontaine du Lycée de France ",
                R.drawable.image5
            ),Post(
                "Livre 6",
                "Voici la description d'un bon Livre de la fable de la fontaine du Lycée de France ",
                R.drawable.image1
            ),Post(
                "Livre 7",
                "Voici la description d'un bon Livre de la fable de la fontaine du Lycée de France ",
                R.drawable.image2
            )
        )

        val adapter = PostAdapter(this, R.layout.item_post, postArray)


        listPosts.adapter = adapter

    }
}