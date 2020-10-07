package com.joaororiz.desafio.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaororiz.desafio.android.R
import com.joaororiz.desafio.android.data.entities.Comic
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicListAdapter(val list: List<Comic>) : RecyclerView.Adapter<ComicListAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListAdapter.ComicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false)
        return ComicViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ComicListAdapter.ComicViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ComicViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comic: Comic) {
            view.tv_name.text = comic.title
            view.tv_description.text = comic.description
        }
    }
}