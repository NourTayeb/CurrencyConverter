package com.nourtayeb.currencyconverter.clean_arch.data.remote.responses.convert

data class ConvertResponse(
    val privacy: String,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int,
    val quotes: Map<String, Double>
)