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
import com.guessaname.marvelapp.data.model.Creator
import kotlinx.android.synthetic.main.card_small.view.*


class CreatorsAdapter() : RecyclerView.Adapter<CreatorsAdapter.CreatorViewHolder>(){


    inner class CreatorViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object :DiffUtil.ItemCallback<Creator> (){
        override fun areItemsTheSame(oldItem: Creator, newItem: Creator): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Creator, newItem: Creator): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        return CreatorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_small,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        val creator = differ.currentList[position]
        holder.itemView.apply {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_foreground)
            Glide.with(this)
                .load("${creator.thumbnail?.path}.${creator.thumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(rv_image_small)
            tv_name_small.text = creator.name
            setOnClickListener {
                onItemClickListener?.let { it(creator) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Creator) -> Unit)? = null

    fun setOnItemClicklistener(listener: (Creator) -> Unit){
        onItemClickListener = listener
    }
}