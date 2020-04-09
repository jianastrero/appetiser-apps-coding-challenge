package com.jianastrero.appetiser_apps_coding_challenge.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jianastrero.appetiser_apps_coding_challenge.EXTRA_MOVIE
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_MOVIE
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_VISIT
import com.jianastrero.appetiser_apps_coding_challenge.activities.base.BaseActivity
import com.jianastrero.appetiser_apps_coding_challenge.adapters.CategorizedMovieAdapter
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ActivityMainBinding
import com.jianastrero.appetiser_apps_coding_challenge.extensions.into
import com.jianastrero.appetiser_apps_coding_challenge.extensions.resize
import com.jianastrero.appetiser_apps_coding_challenge.models.Movie
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Settings
import com.jianastrero.appetiser_apps_coding_challenge.singletons.fromJson
import com.jianastrero.appetiser_apps_coding_challenge.singletons.toJson
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MainViewModel
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory.MyViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory.getInstance())
            .get(MainViewModel::class.java)
    }

    private val adapter = CategorizedMovieAdapter().apply {
        setOnItemClickedListener {
            gotoMovie(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            this.viewModel = this@MainActivity.viewModel

            rvList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                this.adapter = this@MainActivity.adapter
            }
        }

        changeStatusBarColor()

        viewModel.reset()
        viewModel.fetchData().invokeOnCompletion {
            updateList()
        }

        binding.ivFeature.setOnClickListener {
            viewModel.featured?.let {
                gotoMovie(it)
            }
        }

        val lastMovie = Settings.get(SETTINGS_LAST_MOVIE, null.toJson())

        if (lastMovie != null.toJson()) {
            val movie: Movie = lastMovie.fromJson()
            gotoMovie(movie)
        }
    }

    override fun onResume() {
        super.onResume()

        Settings.put(SETTINGS_LAST_MOVIE, null.toJson())

        val lastVisit = Settings.get(SETTINGS_LAST_VISIT, "Never")
        if (lastVisit != "Never") {
            val snackbar = Snackbar
                .make(
                    binding.root,
                    "Last Visit: $lastVisit",
                    Snackbar.LENGTH_INDEFINITE
                ).apply {
                    setAction("close") {
                        dismiss()
                    }
                    setBackgroundTint(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.colorAccent
                        )
                    )
                }
            snackbar.show()
        }
    }

    private fun gotoMovie(movie: Movie) {
        val intent = Intent(this@MainActivity, MovieActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movie)
        startActivity(intent)
    }

    private fun updateList() = CoroutineScope(Dispatchers.Main).launch {
        adapter.submitList(viewModel.categorizedMovieList)

        viewModel.featured?.let {

            val currencyFormat = DecimalFormat("${it.currency} #,##0.00")

            it.artworkUrl100?.resize(800)?.into(binding.ivFeature)
            binding.tvFeatureTitle.text = it.trackName
            binding.tvFeatureGenre.text =
                "${it.primaryGenreName} \uFF65 ${it.releaseDate?.split("-")?.get(0)}"
            binding.tvPrice.text = currencyFormat.format(it.trackPrice)
        }
    }

    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window? = window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = Color.parseColor("#00000000")
        }
    }
}
