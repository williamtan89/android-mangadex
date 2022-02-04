package com.williamtan.mangadexsdk.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamtan.mangadexsdk.R
import com.williamtan.mangadexsdk.databinding.ActivityHomeBinding
import com.williamtan.mangadexsdk.utils.GridLayoutItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn

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
            homeViewModel.loadMangaList(true)
        }

        homeViewModel.getCoverArtPathAvailableSharedFlow().onEach {
            val adapter = binding.rvManga.adapter

            if (adapter is MangaListAdapter) {
                adapter.updateMangaCoverArt(it.first.id, it.second)
            }
        }.shareIn(lifecycleScope, SharingStarted.Eagerly)

        homeViewModel.getMangaListLiveData().observe(this) {
            val adapter = binding.rvManga.adapter

            if (adapter is MangaListAdapter) {
                if (it.isEmpty()) {
                    adapter.setData(emptyList())
                } else if (adapter.itemCount == 0) {
                    adapter.setData(it)
                } else {
                    adapter.insertData(it)
                }
            }
        }

        with(binding.rvManga) {
            adapter = MangaListAdapter()
            layoutManager = GridLayoutManager(this@HomeActivity, 2)

            addItemDecoration(
                GridLayoutItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.manga_list_item_spacing),
                    2
                )
            )

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1)
                        && newState == RecyclerView.SCROLL_STATE_IDLE
                    ) {
                        homeViewModel.loadMangaList(false)
                    }
                }
            })
        }

        homeViewModel.loadMangaList(true)
    }
}