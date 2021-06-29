package com.alfidh.moviez.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfidh.moviez.R
import com.alfidh.moviez.core.ui.MovieAdapter
import com.alfidh.moviez.databinding.ActivityHomeBinding
import com.alfidh.moviez.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        val uri = Uri.parse("moviez://favorite")
        binding.ibFavorite.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        homeViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is com.alfidh.moviez.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is com.alfidh.moviez.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is com.alfidh.moviez.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}