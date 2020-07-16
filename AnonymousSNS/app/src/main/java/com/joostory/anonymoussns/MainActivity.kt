package com.joostory.anonymoussns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val ref = FirebaseDatabase.getInstance().getReference("test")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportActionBar?.title ="글목록"
//
//        floatingActionButton.setOnClickListener{
//            val intent = Intent(this@MainActivity, WriteActivity::class.java)
//
//            startActivity(intent)
//        }

        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val message = snapshot.value.toString()

                Log.d(TAG, message)

                supportActionBar?.title= message
            }
        })


    }
}

class Post {

    var postID =""

    var writerId = ""

    var message = ""

    var writeTime:Any = Any()

    var bgUri = ""

    var commentCount = 0
}
