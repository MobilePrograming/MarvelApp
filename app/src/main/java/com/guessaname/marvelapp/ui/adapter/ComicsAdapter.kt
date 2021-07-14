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
import com.guessaname.marvelapp.data.model.Comic
import kotlinx.android.synthetic.main.card_small.view.*


class ComicsAdapter() : RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>(){


    inner class ComicViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object :DiffUtil.ItemCallback<Comic> (){
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
           return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_small,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = differ.currentList[position]
        holder.itemView.apply {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_foreground)
            Glide.with(this)
                .load("${comic.thumbnail?.path}.${comic.thumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(rv_image_small)
            tv_name_small.text = comic.title
            setOnClickListener {
                onItemClickListener?.let { it(comic) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Comic) -> Unit)? = null

    fun setOnItemClicklistener(listener: (Comic) -> Unit){
        onItemClickListener = listener
    }
}