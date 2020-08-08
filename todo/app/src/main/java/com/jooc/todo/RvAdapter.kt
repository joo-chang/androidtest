package com.jooc.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by yongyi on 2017-11-27.
 */
class RvAdapter(context: Context, items : List<Todo>) :
    RecyclerView.Adapter<RvAdapter.CustomViewHolder>() {
    private var context : Context
    private var items : List<Todo>

    init {
        this.context = context
        this.items = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.title.text = item.toString() + ""
        holder.content.text = item.toString() + "content"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CustomViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var content: TextView

        init {
            title = itemView.findViewById(R.id.item_tv_title)
            content = itemView.findViewById(R.id.item_tv_content)
        }
    }

}

