package com.jianastrero.appetiser_apps_coding_challenge.extensions

import android.widget.ImageView
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.squareup.picasso.Picasso

fun String.into(view: ImageView) =
    Picasso.get()
        .load(this)
        .placeholder(R.drawable.big_cinema)
        .into(view)

fun String.resize(width: Int, height: Int, type: String = "cc") =
    "${substring(0 until length - 13)}${width}x${height}$type.jpg"

fun String.resize(size: Int, type: String = "cc") = resize(size, size, type)