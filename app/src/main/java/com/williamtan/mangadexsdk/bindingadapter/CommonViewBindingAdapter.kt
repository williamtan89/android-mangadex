package com.williamtan.mangadexsdk.bindingadapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide

@BindingAdapter("isVisible")
fun bindViewVisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("isRefreshing")
fun bindViewIsRefreshing(view: SwipeRefreshLayout, isLoading: Boolean) {
    view.isRefreshing = isLoading
}

@BindingAdapter("coverArtUrl")
fun bindImageViewCoverArtId(imageView: ImageView, coverArtUrl: String?) {
    Glide.with(imageView).load(coverArtUrl).into(imageView)
}