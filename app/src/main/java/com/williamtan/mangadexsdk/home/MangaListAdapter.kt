package com.williamtan.mangadexsdk.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexsdk.BR
import com.williamtan.mangadexsdk.R

class MangaListAdapter : RecyclerView.Adapter<MangaListViewHolder>() {
    private val data: MutableList<ItemMangaViewModel> = mutableListOf()
    private val viewTypeToLayoutId: MutableMap<Int, Int> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaListViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewTypeToLayoutId[viewType] ?: 0,
            parent,
            false
        )

        return MangaListViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]

        if (!viewTypeToLayoutId.containsKey(item.viewType)) {
            viewTypeToLayoutId[item.viewType] = item.layoutId
        }

        return data[position].viewType
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
                MangaViewModel(
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
            MangaViewModel(
                title = it.title
            )
        })

        notifyItemInserted(currentSize + 1)
    }
}

class MangaListViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(itemMangaViewModel: ItemMangaViewModel) {
        binding.setVariable(BR.itemViewModel, itemMangaViewModel)
    }
}

class MangaViewModel(val title: String) : ItemMangaViewModel {
    override val layoutId: Int = R.layout.item_manga
    override val viewType: Int = MangaListItemViewType.MangaViewType.viewType
}

interface ItemMangaViewModel {
    val layoutId: Int
    val viewType: Int
        get() = 0
}

enum class MangaListItemViewType(val viewType: Int) {
    MangaViewType(1)
}