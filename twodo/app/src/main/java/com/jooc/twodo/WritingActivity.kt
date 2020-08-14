package com.jooc.twodo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_writing.*
import kotlin.math.min

class WritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)



        ic_done.setOnClickListener{

            val inputPlace = placeInput.text.toString()
            val inputTodo = todoInput.text.toString()

            val mintent = Intent(this, MainActivity::class.java)
            mintent.putExtra("place",inputPlace)
            mintent.putExtra("todo",inputTodo)

            startActivity(mintent)

        }

    }
}