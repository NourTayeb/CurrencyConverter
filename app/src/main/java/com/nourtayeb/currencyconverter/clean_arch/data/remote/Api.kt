package com.nourtayeb.currencyconverter.clean_arch.data.remote

import com.nourtayeb.currencyconverter.clean_arch.data.remote.responses.convert.ConvertResponse
import com.nourtayeb.currencyconverter.clean_arch.data.remote.responses.currencies.CurrenciesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("list")
    fun getCurrencies(@Query("access_key") access_key: String): Single<CurrenciesResponse>

    @GET("live")
    fun convert(@Query("access_key") access_key: String,
                      @Query("currencies") currencies:String,
                      @Query("source") source:String,
                      @Query("format") format:String
                      ): Single<ConvertResponse>

}