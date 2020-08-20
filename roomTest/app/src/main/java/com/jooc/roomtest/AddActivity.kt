package com.jooc.roomtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private var todoDb : CatDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        todoDb = CatDB.getInstance(this)

        /* 새로운 cat 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
        val addRunnable = Runnable {
            val newTodo = Cat()
            newTodo.catName = addName.text.toString()
            newTodo.lifeSpan = addLifeSpan.text.toString().toInt()
            newTodo.origin = addOrigin.text.toString()
            todoDb?.catDao()?.insert(newTodo)
        }

        addBtn.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        CatDB.destroyInstance()
        super.onDestroy()
    }
}