package com.jooc.twodo

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jooc.twodo.databinding.ListItemBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        main_write_btn.setOnClickListener{view ->
            val intent = Intent(this, WritingActivity::class.java)
            startActivity(intent)


        }
        val mintent = getIntent()
        val todoList = arrayListOf<Post>()
//            item_content.setText(mintent.getStringExtra("todo"))
//            item_place.setText((mintent.getStringExtra("place")))
        todoList.add(Post(mintent.getStringExtra("place").toString(),mintent.getStringExtra("todo").toString()))

        main_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(todoList){post ->
                Toast.makeText(this@MainActivity, "$post",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class PostAdapter(val items: List<Post>, private val clickListener: (post: Post)-> Unit):
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    class PostViewHolder(val binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent, false)
        val viewHolder = PostViewHolder(ListItemBinding.bind(view))
        view.setOnClickListener{
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.post = items[position]
    }
}