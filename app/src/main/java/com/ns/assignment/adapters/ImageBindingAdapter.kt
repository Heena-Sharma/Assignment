

package com.ns.assignment.adapters

import androidx.appcompat.widget.AppCompatImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ns.assignment.R


fun bindImageFromUrl(view: AppCompatImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
         Glide.with(view.context).load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
}

