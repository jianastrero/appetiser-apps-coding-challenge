package com.jianastrero.appetiser_apps_coding_challenge.extensions

import android.content.res.Resources

/**
 * Return the dp equivalent of a number
 */
val Number.dp: Float
    get() = this.toFloat() * Resources.getSystem().displayMetrics.density

/**
 * Return the px equivalent of a number
 */
val Number.px: Float
    get() = this.toFloat() / Resources.getSystem().displayMetrics.density