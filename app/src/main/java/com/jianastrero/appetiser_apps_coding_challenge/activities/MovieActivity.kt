package com.jianastrero.appetiser_apps_coding_challenge.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jianastrero.appetiser_apps_coding_challenge.EXTRA_MOVIE
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_MOVIE
import com.jianastrero.appetiser_apps_coding_challenge.activities.base.BaseActivity
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ActivityMovieBinding
import com.jianastrero.appetiser_apps_coding_challenge.extensions.into
import com.jianastrero.appetiser_apps_coding_challenge.extensions.resize
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Settings
import com.jianastrero.appetiser_apps_coding_challenge.singletons.toJson
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MovieViewModel
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory.MyViewModelFactory

class MovieActivity : BaseActivity() {

    private lateinit var binding: ActivityMovieBinding

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory.getInstance())
            .get(MovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        if (intent == null) {
            finish()
        } else {
            viewModel.movie = intent.getParcelableExtra(EXTRA_MOVIE)
        }

        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.title = ""

        val previewURL = viewModel.movie?.previewUrl
        binding.ivPlay.isVisible = previewURL != null
        if (previewURL != null)
            binding.ivImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(
                    Uri.parse(previewURL),
                    "video/*"
                )
                startActivity(Intent.createChooser(intent, "Watch Preview using"))
            }

        viewModel.movie?.artworkUrl100?.resize(300)?.into(binding.ivImage)
    }

    override fun onPause() {
        super.onPause()
        Settings.put(SETTINGS_LAST_MOVIE, viewModel.movie.toJson())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}