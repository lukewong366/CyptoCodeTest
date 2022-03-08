package com.example.cyptocodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cyptocodetest.databinding.ActivityDemoBinding
import com.example.cyptocodetest.view.CurrencyListFragment
import com.example.cyptocodetest.viewmodel.DemoActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {
    private val viewModel: DemoActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDemoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_demo)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CurrencyListFragment.newInstance()).commit()
        }
    }
}