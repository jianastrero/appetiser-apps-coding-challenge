package com.jianastrero.appetiser_apps_coding_challenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.ResultAdapter
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ActivityMainBinding
import com.jianastrero.appetiser_apps_coding_challenge.extensions.dp
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MainViewModel
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory.getInstance())
            .get(MainViewModel::class.java)
    }

    private val adapter: ResultAdapter by lazy {
        ResultAdapter(viewModel.list)
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
    }

    override fun onResume() {
        super.onResume()

        viewModel.reset()

        // if (viewModel.lastVisit.get().equals("Never", true))
        //     binding.tvLastVisit.isVisible = false

        viewModel.fetchData().invokeOnCompletion {
            updateList()
        }
    }

    private fun updateList() = CoroutineScope(Dispatchers.Main).launch {
        adapter.notifyDataSetChanged()
    }
}
