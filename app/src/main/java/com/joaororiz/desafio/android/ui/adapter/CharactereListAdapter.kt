package com.joaororiz.desafio.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaororiz.desafio.android.R
import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.extension.getLargeUrl
import com.joaororiz.desafio.android.extension.setImageFromUrl
import kotlinx.android.synthetic.main.item_character.view.*

class CharactereListAdapter(var list: MutableList<Character> = mutableListOf()) : RecyclerView.Adapter<CharactereListAdapter.CharactereViewHolder>() {
    var eventClick: ((item: Character) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactereViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharactereViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CharactereViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(listCharacter: List<Character>) {
        val position = list.lastIndex-1
        list.addAll(listCharacter)
       notifyDataSetChanged()
    }

    fun clearList() {
        list= mutableListOf()
        notifyDataSetChanged()
    }

    inner class CharactereViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character) {
            itemView.setOnClickListener {
                eventClick?.invoke( character)
            }
            itemView.tv_name.text = character.name
            itemView.img_background.setImageFromUrl(character.thumbnail.getLargeUrl(), R.drawable.ic_round_account_circle)
        }
    }
}