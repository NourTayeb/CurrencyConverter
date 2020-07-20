package com.nourtayeb.currencyconverter.common.extensions

import android.content.Intent
import android.os.Parcelable
import java.io.Serializable

fun Intent.putExtra(key: String, value: Any?) {
    when (value){
        is String -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Boolean -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        is Serializable -> putExtra(key, value)
        else -> throw IllegalArgumentException("Parameter type not allowed")

    }
}
