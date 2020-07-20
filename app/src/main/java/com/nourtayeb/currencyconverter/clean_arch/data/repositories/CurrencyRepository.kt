package com.nourtayeb.currencyconverter.clean_arch.data.repositories

import com.nourtayeb.currencyconverter.common.API_KEY
import com.nourtayeb.currencyconverter.clean_arch.data.mapper.CurrencyMapper
import com.nourtayeb.currencyconverter.clean_arch.data.local.db.AppDatabase
import com.nourtayeb.currencyconverter.clean_arch.data.mapper.ConvertionMapper
import com.nourtayeb.currencyconverter.clean_arch.data.remote.Api
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.repositories.CurrenciesRepository
import com.nourtayeb.currencyconverter.common.extensions.toCommaSeperatedString
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val database: AppDatabase,
    val restClient: Api,
    val currencyMapper:CurrencyMapper,
    val convertionMapper:ConvertionMapper
) : CurrenciesRepository {


    override fun convertCurrencies(
        defaultCurrency: String,
        currencies: List<Currency>
    ): Single<List<Currency>> {
            return restClient.convert(API_KEY,currencies.map { it.code }.toCommaSeperatedString(),defaultCurrency,"1").flatMap {
                Single.just(convertionMapper.RetrofitToDomain(it.quotes,currencies))
            }
    }


    override fun getCurrencies(): Single<List<Currency>> {
        return database.currenciesDao().getCurrenciesList()
            .flatMap{
                if(it.isEmpty()){
                    restClient.getCurrencies(API_KEY)
                        .map { response ->
                            if (response.success) {
                                database.runInTransaction {
                                    database.currenciesDao().insertAllCurrencies(currencyMapper.RetrofitToRoom(response.currencies))
                                }
                                currencyMapper.RetrofitToDomain(response.currencies)
                            }else{
                                mutableListOf()
                            }
                        }
                }else{
                    Single.just<List<Currency>>(currencyMapper.RoomToDomain(it))
                }
            }
    }

    override fun addCurrencyToFav(currency: Currency):Single<Int>{
        return database.currenciesDao().update(currencyMapper.DomainToRoom(currency))
    }

    override fun getFavorite(): Single<List<Currency>> {
        return database.currenciesDao().getFavorite().flatMap{
            Single.just<List<Currency>>(currencyMapper.RoomToDomain(it))
        }
    }

}


