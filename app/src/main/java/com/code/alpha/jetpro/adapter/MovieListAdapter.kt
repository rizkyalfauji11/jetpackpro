package com.code.alpha.jetpro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.alpha.jetpro.R
import com.code.alpha.jetpro.model.source.local.entity.Movie

class MovieListAdapter(
    private val ctx: Context, private val movieList: List<Movie>,
    private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position], ctx, listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtTitle = view.findViewById<TextView>(R.id.title)
        private val txtYear = view.findViewById<TextView>(R.id.year)
        private val ratingMovie = view.findViewById<RatingBar>(R.id.rating)
        private val imgCover = view.findViewById<ImageView>(R.id.cover)
        fun bind(item: Movie, ctx: Context, listener: (Movie) -> Unit) {
            txtTitle.text = item.title
            txtYear.text = item.year
            ratingMovie.rating = item.rating.toString().toFloat() / 2
            itemView.setOnClickListener {
                listener(item)
            }
            Glide.with(ctx).load(item.photo)
                .override(200, 300)
                .fitCenter()
                .into(imgCover)
        }
    }
}