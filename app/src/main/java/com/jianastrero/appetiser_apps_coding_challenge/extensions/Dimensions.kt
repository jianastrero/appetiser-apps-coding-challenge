package com.jianastrero.appetiser_apps_coding_challenge.extensions

import android.content.res.Resources

val Number.dp: Float
    get() = this.toFloat() * Resources.getSystem().displayMetrics.density

val Number.px: Float
    get() = this.toFloat() / Resources.getSystem().displayMetrics.density