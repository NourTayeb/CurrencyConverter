package com.nourtayeb.currencyconverter.common


val API_BASE_URL = "http://api.currencylayer.com/"
val API_KEY = "189a88f3c1c2587c463644af625a7119"
val USD = "USD"
val DEFAULT_CURRENCY: String = "default_currency"
val REQUEST_FOR_DEFAULT_CURRENCY: String = "request_default"
val MANAGE_FAVORITE: String = "manage_favorite"
val SUPPORTED_CURRENCIES = mapOf<String,Double>("USD" to 1.0,"SAR" to 0.27,"EUR" to 1.13,"JPY" to 0.0091,"UAE" to 0.27)