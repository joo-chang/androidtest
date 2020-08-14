package com.jooc.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*


class MainActivity : AppCompatActivity() {


    private var memos = ArrayList<Todo>();
    private val llm = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_view.setHasFixedSize(true)
        main_view.layoutManager = llm

        main_write_btn.setOnClickListener{
//            var title : String  = item_tv_title.text.toString()
//            var content : String = item_tv_content.text.toString()
            startActivity(Intent(this,writing::class.java))
            finish()
            var memo = Todo(intent.getStringExtra("place_text"),intent.getStringExtra("todo_text"))
            memos.add(memo)

            item_tv_title.setText("")
            item_tv_content.setText("")

            main_view.adapter = RvAdapter(applicationContext, memos)
        }

        if(intent.hasExtra("place_text")){

        }
    }
}
//    var todoList = arrayListOf<Todo>(
//        Todo("다이소","물통사기"),
//        Todo("맥도날드","햄버거사기"),
//        Todo("애플","에어팟 수리")
//
//    )

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



//    private var rv: RecyclerView? = null
//    private var llm: LinearLayoutManager? = null
//    private var count: MutableList<Int>? = null
//    private var i = 0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        rv = findViewById<View>(R.id.main_view) as RecyclerView
//        llm = LinearLayoutManager(this)
//        count = ArrayList()
//        rv!!.setHasFixedSize(true)
//        rv!!.layoutManager = llm
//
//        main_write_btn.setOnClickListener{
//            startActivity(Intent(this@MainActivity, writing::class.java))
//        }
//    }
//
//    fun onClick(view: View) {
//        when (view.id) {
//            R.id.main_btn_add -> {
//                i++
//                count!!.add(i)
//                val adapter = RvAdapter(application, count, i)
//                rv!!.adapter = adapter
//                Log.d("Count", count.toString() + "")
//            }
//        }
//    }
//}