package com.guessaname.marvelapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.data.model.Character
import kotlinx.android.synthetic.main.card_big.view.*

class BookmarksAdapter() : RecyclerView.Adapter<BookmarksAdapter.BookmarkViewHolder>(){

    inner class BookmarkViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object :DiffUtil.ItemCallback<Character> (){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.charactername == newItem.charactername
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
           return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_big,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.itemView.apply {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_foreground)
            Glide.with(this)
                .load("${character.characterthumbnail?.path}.${character.characterthumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(rv_image_big)
            tv_name_big.text = character.charactername
            tv_character_short_bio.text = character.characterdescription
            setOnClickListener {
                onItemClickListener?.let { it(character) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Character) -> Unit)? = null

    fun setOnItemClicklistener(listener: (Character) -> Unit){
        onItemClickListener = listener
    }

}