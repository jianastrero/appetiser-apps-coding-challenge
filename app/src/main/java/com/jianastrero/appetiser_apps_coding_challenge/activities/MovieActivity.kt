package com.jianastrero.appetiser_apps_coding_challenge.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jianastrero.appetiser_apps_coding_challenge.EXTRA_MOVIE
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ActivityMovieBinding
import com.jianastrero.appetiser_apps_coding_challenge.extensions.into
import com.jianastrero.appetiser_apps_coding_challenge.extensions.resize
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MovieViewModel
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory.MyViewModelFactory

class MovieActivity : AppCompatActivity() {

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
}
