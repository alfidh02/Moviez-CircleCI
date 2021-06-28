package com.alfidh.moviez.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfidh.moviez.core.R
import com.alfidh.moviez.core.domain.model.Movie
import com.alfidh.moviez.core.databinding.ItemListMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(movieListData: List<Movie>?) {
        if (movieListData == null) return
        listData.clear()
        listData.addAll(movieListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${data.image}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_black))
                    .error(R.drawable.ic_broken_image_black)
                    .into(rivPoster)

                tvTitle.text = data.title
                tvDate.text = data.date
                tvRate.text = data.rate.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}