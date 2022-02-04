package com.williamtan.mangadexsdk.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexsdk.R
import com.williamtan.mangadexsdk.home.MangaListAdapter
import com.williamtan.mangadexsdk.utils.GridLayoutItemDecoration

@BindingAdapter("mangaListData")
fun bindMangaListAdapter(recyclerView: RecyclerView, data: List<Manga>) {
    val adapter = if (recyclerView.adapter != null && recyclerView.adapter is MangaListAdapter) {
        recyclerView.adapter as MangaListAdapter
    } else {
        val bindableRecyclerAdapter = MangaListAdapter()
        val gridSize = 2

        recyclerView.adapter = bindableRecyclerAdapter
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, gridSize)
        recyclerView.addItemDecoration(
            GridLayoutItemDecoration(
                recyclerView.resources.getDimensionPixelSize(R.dimen.manga_list_item_spacing),
                gridSize
            )
        )

        bindableRecyclerAdapter
    }

    adapter.setData(data)
}