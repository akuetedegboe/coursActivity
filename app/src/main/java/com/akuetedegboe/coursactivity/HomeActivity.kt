package com.akuetedegboe.coursactivity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.akuetedegboe.coursactivity.data.Post
import com.akuetedegboe.coursactivity.db.Database

class HomeActivity : AppCompatActivity() {
    lateinit var listPosts: ListView
    var postArray = ArrayList<Post>()
    lateinit var adapterPosts: PostAdapter

    lateinit var db: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listPosts = findViewById(R.id.list_posts)

        db = Database(this)

        postArray = db.findPosts()

        adapterPosts = PostAdapter(this, R.layout.item_post, postArray)

        listPosts.adapter = adapterPosts


        //créer un ecouteur d'evenement sur les items
        listPosts.setOnItemClickListener { adapterView, view, position, id ->

            val clickedPost = postArray[position]
            Intent(this, AddPostActivity::class.java).also {
                it.putExtra("mode","edit")
                it.putExtra("id", clickedPost.id)
                it.putExtra("titre", clickedPost.titre)
                it.putExtra("description", clickedPost.description)
                it.putExtra("image", clickedPost.image)
                startActivity(it)
            }

            //   Toast.makeText(this,"Position : $position",Toast.LENGTH_LONG).show()
        }
        registerForContextMenu(listPosts)

    }

    override fun onResume() {
        super.onResume()
        postArray = db.findPosts()

        adapterPosts = PostAdapter(this, R.layout.item_post, postArray)

        listPosts.adapter = adapterPosts

        //créer un ecouteur d'evenement sur les items
        listPosts.setOnItemClickListener { adapterView, view, position, id ->
            val clickedPost = postArray[position]
            Intent(this, AddPostActivity::class.java).also {
                println("La position est ${clickedPost.titre}")
                it.putExtra("titre ", clickedPost.titre)
                startActivity(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAdd -> {

                Intent(this, AddPostActivity::class.java).also {
                    it.putExtra("mode","add")
                    startActivity(it)
                }
                //  Toast.makeText(this, "Add Post", Toast.LENGTH_SHORT).show()
            }

            R.id.itemConfig -> {
                Toast.makeText(this, "Config Appt", Toast.LENGTH_SHORT).show()
            }

            R.id.itemLogout -> {
                //  finish()
                showLogoutConfirmDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info: AdapterView.AdapterContextMenuInfo =
            item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position: Int = info.position
        when (item.itemId) {
            R.id.itemContextAfficher -> {

                    val clickedPost = postArray[position]
                    Intent(this, AddPostActivity::class.java).also {
                        it.putExtra("mode","edit")
                        it.putExtra("id", clickedPost.id)
                        it.putExtra("titre", clickedPost.titre)
                        it.putExtra("description", clickedPost.description)
                        it.putExtra("image", clickedPost.image)
                        startActivity(it)
                    }


              //  Toast.makeText(this, "Show Details ${position}", Toast.LENGTH_SHORT).show()

            }

            R.id.itemContextSupprimer -> {

                val resultDelete = db.deletePost(postArray[position].id)
                if (resultDelete) {
                    postArray.removeAt(position)
                    Toast.makeText(this, "Suppression successfull", Toast.LENGTH_LONG).show()
//
                   adapterPosts.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "Erreur de suppression", Toast.LENGTH_LONG).show()
                }
             //   Toast.makeText(this, "Delete Details ${position}", Toast.LENGTH_SHORT).show()

            }
        }
        return super.onContextItemSelected(item)
    }

    fun showLogoutConfirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Etes-vous sure de vouloir quitter ??")
        builder.setPositiveButton("Oui") { dialogInterface, id ->
            finish()
        }
        builder.setNegativeButton("Non") { dialogInterface, id ->
            dialogInterface.dismiss()
        }
        builder.setNeutralButton("Annuler") { dialogInterface, id ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}