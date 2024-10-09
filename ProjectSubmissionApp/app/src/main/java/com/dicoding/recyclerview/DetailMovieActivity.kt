package com.dicoding.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.recyclerview.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding

    companion object {
        const val MOVIE_TITLE = "MOVIE_TITLE"
        const val MOVIE_OVERVIEW = "MOVIE_OVERVIEW"
        const val MOVIE_PHOTO = "MOVIE_PHOTO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Movie Page"

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        //     Get data from intent
        val movieTitle = intent.getStringExtra(MOVIE_TITLE)
        val movieOverview = intent.getStringExtra(MOVIE_OVERVIEW)
        val moviePhoto = intent.getStringExtra(MOVIE_PHOTO)

        // Bind the data to the views
        binding.tvMovieTitle.text = movieTitle
        binding.tvMovieOverview.text = movieOverview
        Glide.with(this)
            .load(moviePhoto)
            .into(binding.imgMoviePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
               onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_share -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Check out this movie: ${binding.tvMovieTitle.text} - ${binding.tvMovieOverview.text}")
                }
                startActivity(Intent.createChooser(shareIntent, "Share with via"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}