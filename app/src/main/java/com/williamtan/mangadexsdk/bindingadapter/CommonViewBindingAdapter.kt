package com.williamtan.mangadexsdk.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

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