package com.jooc.roomtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private var todoDb : TodoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        todoDb = TodoDB.getInstance(this)

        /* 새로운 cat 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
        val addRunnable = Runnable {
            val newTodo = Todo()
            newTodo.catName = addName.text.toString()
            newTodo.lifeSpan = addLifeSpan.text.toString().toInt()
            newTodo.origin = addOrigin.text.toString()
            todoDb?.todoDao()?.insert(newTodo)
        }

        addBtn.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        ic_return.setOnClickListener{
            onBackPressed()
        }
    }

    override fun onDestroy() {
        TodoDB.destroyInstance()
        super.onDestroy()
    }
}