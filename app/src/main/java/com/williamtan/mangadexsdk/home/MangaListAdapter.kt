package com.williamtan.mangadexsdk.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexsdk.BR
import com.williamtan.mangadexsdk.R

class MangaListAdapter : RecyclerView.Adapter<MangaListViewHolder>() {
    private val data: MutableList<MangaListItemData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaListViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_manga,
            parent,
            false
        )

        return MangaListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MangaListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<Manga>) {
        with(data) {
            clear()

            addAll(newData.map {
                MangaListItemData(
                    mangaId = it.id,
                    title = it.title
                )
            })
        }

        // since the whole data has been replaced
        notifyDataSetChanged()
    }

    fun insertData(newData: List<Manga>) {
        val currentSize = data.size

        newData.mapTo(data, {
            MangaListItemData(
                mangaId = it.id,
                title = it.title
            )
        })

        notifyItemInserted(currentSize + 1)
    }

    fun updateMangaCoverArt(mangaId: String, coverArtUrl: String) {
        val mangaIndex = data.indexOfFirst {
            it.mangaId == mangaId
        }

        if (mangaIndex >= 0) {
            data[mangaIndex].coverArtUrl = coverArtUrl
            notifyItemChanged(mangaIndex)
        }
    }
}

class MangaListViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(itemData: MangaListItemData) {
        binding.setVariable(BR.itemData, itemData)
    }
}

data class MangaListItemData(
    val mangaId: String,
    val title: String,
    var coverArtUrl: String? = null
)