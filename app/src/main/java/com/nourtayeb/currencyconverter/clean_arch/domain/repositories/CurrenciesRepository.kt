package com.nourtayeb.currencyconverter.clean_arch.domain.repositories

import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import io.reactivex.Single

interface CurrenciesRepository {
    fun getCurrencies(): Single<List<Currency>>
    fun getFavorite(): Single<List<Currency>>
    fun addCurrencyToFav(currency: Currency): Single<Int>
    fun convertCurrencies(defaultCurrency: String,currencies:List<Currency>): Single<List<Currency>>
}