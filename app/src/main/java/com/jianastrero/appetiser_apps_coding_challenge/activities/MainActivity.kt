package com.jianastrero.appetiser_apps_coding_challenge.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.ResultAdapter
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ActivityMainBinding
import com.jianastrero.appetiser_apps_coding_challenge.extensions.bigger
import com.jianastrero.appetiser_apps_coding_challenge.extensions.into
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MainViewModel
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory.MyViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory.getInstance())
            .get(MainViewModel::class.java)
    }

    private val adapter: ResultAdapter by lazy {
        ResultAdapter(viewModel.trendingList)
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
    }

    override fun onResume() {
        super.onResume()

        if (viewModel.lastVisit.get().equals("Never", true))
            binding.tvLastVisit.isVisible = false
    }

    private fun updateList() = CoroutineScope(Dispatchers.Main).launch {
        adapter.notifyDataSetChanged()

        viewModel.featured?.let {

            val currencyFormat = DecimalFormat("${it.currency} #,##0.00")

            it.artworkUrl100.bigger(800).into(binding.ivFeature)
            binding.tvFeatureTitle.text = it.trackName
            binding.tvFeatureGenre.text = it.primaryGenreName
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
