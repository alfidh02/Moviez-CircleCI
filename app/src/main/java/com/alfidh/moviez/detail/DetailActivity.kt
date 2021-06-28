package com.alfidh.moviez.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alfidh.moviez.R
import com.alfidh.moviez.core.domain.model.Movie
import com.alfidh.moviez.databinding.ActivityDetailBinding
import com.alfidh.moviez.databinding.ContentDetailMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentBinding = binding.detailContent
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let { movie ->
            contentBinding.tvTitleDetail.text = movie.title
            contentBinding.tvReleaseDate.text = movie.date
            contentBinding.tvRateDetail.text = movie.rate.toString()
            contentBinding.tvDesc.text = movie.desc

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500${movie.image}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_black))
                .error(R.drawable.ic_broken_image_black)
                .into(contentBinding.rivPosterDetail)

            var statusFavorite = movie.favorite
            setStatusFavorite(statusFavorite)
            contentBinding.btnFav.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
                setToast(statusFavorite)
            }
        }
    }

    private fun setToast(statusFavorite: Boolean) {
        Toast.makeText(
            this@DetailActivity,
            if (statusFavorite) "Berhasil ditambahkan ke favorit" else "Dihapus dari favorit",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        contentBinding.btnFav.setImageDrawable(
            if (statusFavorite) ContextCompat.getDrawable(
                this,
                R.drawable.ic_favorite_solid
            ) else ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        )
    }
}