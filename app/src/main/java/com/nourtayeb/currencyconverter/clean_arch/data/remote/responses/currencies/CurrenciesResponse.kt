package com.nourtayeb.currencyconverter.clean_arch.data.remote.responses.currencies

import com.nourtayeb.currencyconverter.common.extensions.fromObjectToMap
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency


data class
CurrenciesResponse(
    val currencies: Currencies,
    val privacy: String,
    val success: Boolean,
    val terms: String
)
{

    fun getCurrencyList():List<Currency>{
        val map= fromObjectToMap(currencies)
        val mutableList = mutableListOf<Currency>()
        map?.forEach { (key, value) -> mutableList.add(Currency(key as String,value as String)) }
        return mutableList
    }
}