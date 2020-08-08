package com.jooc.recyclertest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainRvAdapter(val context: Context, val dogList: ArrayList<Dog>):
        RecyclerView.Adapter<>(){

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ??? {
    }

    override fun getItemCount(): Int {
    }

    override fun onBindViewHolder(holder: ???, position: Int) {
    }

}