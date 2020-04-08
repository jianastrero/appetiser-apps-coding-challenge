package com.jianastrero.appetiser_apps_coding_challenge.extensions

import android.widget.ImageView
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.squareup.picasso.Picasso

fun String.into(view: ImageView) =
    Picasso.get()
        .load(this)
        .placeholder(R.drawable.big_cinema)
        .into(view)

fun String.bigger(width: Int, height: Int) =
    "${substring(0 until length - 13)}${width}x${height}cc.jpg"

fun String.bigger(size: Int) = bigger(size, size)