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

class CharacterListAdapter(var list: MutableList<Character> = mutableListOf()) : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {
    var eventClick: ((item: Character) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(listCharacter: List<Character>) {
        list.addAll(listCharacter)
       notifyDataSetChanged()
    }

    fun clearList() {
        list= mutableListOf()
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character) {
            itemView.setOnClickListener {
                eventClick?.invoke( character)
            }
            itemView.tv_name.text = character.name
            itemView.img_background.setImageFromUrl(character.thumbnail?.getLargeUrl(), R.drawable.ic_round_account_circle)
        }
    }

}