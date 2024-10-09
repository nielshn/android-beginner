package com.dicoding.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.recyclerview.databinding.ItemRowMovieBinding

class ListMovieAdapter(
    private val listMovie: ArrayList<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.binding.tvItemName.text = movie.title
        holder.binding.tvItemDescription.text = movie.description
        Glide.with(holder.itemView.context)
            .load(movie.imageUrl)
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener { onClick(movie) }
    }
}