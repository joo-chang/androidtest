package com.jooc.todo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    var todoList = arrayListOf<Todo>(
        Todo("다이소","물통사기"),
        Todo("맥도날드","햄버거사기"),
        Todo("애플","에어팟 수리")

    )

//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        setContentView(R.layout.activity_main)
//
//        val rvAdapter = RvAdapter(this, todoList)
//        main_view.adapter = rvAdapter
//
//        val lm = LinearLayoutManager(this)
//        main_view.layoutManager = lm
//        main_view.setHasFixedSize(true)
//    }



    private var rv: RecyclerView? = null
    private var llm: LinearLayoutManager? = null
    private var count: MutableList<Int>? = null
    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById<View>(R.id.main_view) as RecyclerView
        llm = LinearLayoutManager(this)
        count = ArrayList()
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = llm

        main_write_btn.setOnClickListener{
            startActivity(Intent(this@MainActivity, writing::class.java))
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.main_write_btn -> {
                i++
                count!!.add(i)
                val adapter = RvAdapter(application,todoList,count,i)
                rv!!.adapter = adapter
                Log.d("Count", count.toString() + "")
            }
        }
    }
}