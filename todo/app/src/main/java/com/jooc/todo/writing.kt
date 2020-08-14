package com.jooc.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_writing.*

class writing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)

        ic_done.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("place_text", placeInput.toString())
            intent.putExtra("todo_text", todoInput.toString())
            startActivity(intent)
        }

        ic_return.setOnClickListener{
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}
