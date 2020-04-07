package com.jianastrero.appetiser_apps_coding_challenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ActivityMainBinding
import com.jianastrero.appetiser_apps_coding_challenge.extensions.dp
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MainViewModel
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory.getInstance())
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()

        viewModel.reset()

        if (viewModel.lastVisit.get().equals("Never", true))
            binding.tvLastVisit.isVisible = false
    }
}
