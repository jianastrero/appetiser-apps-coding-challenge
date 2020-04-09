package com.jianastrero.appetiser_apps_coding_challenge.extensions

import android.widget.ImageView
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.squareup.picasso.Picasso

/**
 * Shorthand way of setting an image to an imageview, because picasso is a boilerplate
 *
 * @param view the view to put the image on
 */
fun String.into(view: ImageView) =
    Picasso.get()
        .load(this)
        .placeholder(R.drawable.big_cinema)
        .into(view)

/**
 * Only works for the iTunes Search API Situation. Resize the url image.
 *
 * @param width Desired new width
 * @param height Desired new height
 * @param type Type of the image, bb = get smallest, cc = get largest, bc = rounded
 */
fun String.resize(width: Int, height: Int, type: String = "cc") =
    "${substring(0 until length - 13)}${width}x${height}$type.jpg"

/**
 * Only works for the iTunes Search API Situation. Resize the url image.
 *
 * @param size Desired size of the image
 * @param type Type of the image, bb = get smallest, cc = get largest, bc = rounded
 */
fun String.resize(size: Int, type: String = "cc") = resize(size, size, type)