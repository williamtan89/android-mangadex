package com.williamtan.mangadexsdk.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexsdk.home.MangaListAdapter

@BindingAdapter("mangaListData")
fun bindMangaListAdapter(recyclerView: RecyclerView, data: List<Manga>) {
    val adapter = if (recyclerView.adapter != null && recyclerView.adapter is MangaListAdapter) {
        recyclerView.adapter as MangaListAdapter
    } else {
        val bindableRecyclerAdapter = MangaListAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)

        bindableRecyclerAdapter
    }

    adapter.setData(data)
}