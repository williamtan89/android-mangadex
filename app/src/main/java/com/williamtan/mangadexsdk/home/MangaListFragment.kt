package com.williamtan.mangadexsdk.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamtan.mangadexsdk.R
import com.williamtan.mangadexsdk.databinding.FragmentHomeBinding
import com.williamtan.mangadexsdk.utils.GridLayoutItemDecoration
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn

class MangaListFragment : Fragment() {
    private val viewModel: MangaListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: FragmentHomeBinding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_home)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.srlManga.setOnRefreshListener {
            viewModel.loadMangaList(true)
        }

        viewModel.getCoverArtPathAvailableSharedFlow().onEach {
            val adapter = binding.rvManga.adapter

            if (adapter is MangaListAdapter) {
                adapter.updateMangaCoverArt(it.first.id, it.second)
            }
        }.shareIn(lifecycleScope, SharingStarted.Eagerly)

        viewModel.getMangaListLiveData().observe(this) {
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
            layoutManager = GridLayoutManager(requireContext(), 2)

            addItemDecoration(
                GridLayoutItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.manga_list_item_spacing),
                    2
                )
            )

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (
                        !recyclerView.canScrollVertically(1) &&
                        newState == RecyclerView.SCROLL_STATE_IDLE
                    ) {
                        viewModel.loadMangaList(false)
                    }
                }
            })
        }

        viewModel.loadMangaList(true)
    }
}