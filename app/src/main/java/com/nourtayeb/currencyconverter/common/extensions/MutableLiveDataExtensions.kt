package com.nourtayeb.currencyconverter.common.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyObservers() {
    value = value
}
