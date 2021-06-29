package com.alfidh.moviez.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfidh.moviez.core.ui.MovieAdapter
import com.alfidh.moviez.detail.DetailActivity
import com.alfidh.moviez.favorite.databinding.ActivityFavoriteBinding
import com.alfidh.moviez.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this, { dataMovie ->
            movieAdapter.setData(dataMovie)
            binding.viewEmpty.root.visibility =
                if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvFavMovie) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}