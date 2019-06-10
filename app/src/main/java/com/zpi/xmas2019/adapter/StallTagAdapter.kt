package com.zpi.xmas2019.adapter

import android.content.Context
import android.view.LayoutInflater
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zpi.xmas2019.R
import kotlinx.android.synthetic.main.tag_row.view.*

class StallTagAdapter @Inject constructor( val tags :ArrayList<String>, val context : Context) : RecyclerView.Adapter<StallTagAdapter.ViewHolder>() {

    var layoutInflater : LayoutInflater? = null
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.tag_row, parent, false))
    }

    override fun getItemCount(): Int {
       return tags.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag_row.text = tags.get(position)
        holder.itemView.tag_row.textSize = 15F
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tag = itemView?.tag
        init {
            itemView?.setOnClickListener {
                onItemClick?.invoke(tags[adapterPosition])
            }
        }
    }

    annotation class Inject

}