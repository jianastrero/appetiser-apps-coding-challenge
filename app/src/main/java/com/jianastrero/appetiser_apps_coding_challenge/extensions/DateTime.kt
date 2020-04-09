package com.jianastrero.appetiser_apps_coding_challenge.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val readableDateTimeFormat = SimpleDateFormat("MMMM dd, yyyy hh:mm aa", Locale.US)

fun Date.toReadable() = readableDateTimeFormat.format(this)