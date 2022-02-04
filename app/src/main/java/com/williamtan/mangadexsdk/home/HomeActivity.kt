package com.williamtan.mangadexsdk.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.williamtan.mangadexsdk.R
import com.williamtan.mangadexsdk.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel

        binding.srlManga.setOnRefreshListener {
            homeViewModel.loadMangaList()
        }

        homeViewModel.loadMangaList()
    }
}