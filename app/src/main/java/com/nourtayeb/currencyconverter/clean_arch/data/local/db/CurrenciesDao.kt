package com.nourtayeb.currencyconverter.clean_arch.data.local.db

import androidx.room.*
import com.nourtayeb.currencyconverter.clean_arch.data.local.db.entities.CurrencyRoom
import io.reactivex.Single


@Dao
interface CurrenciesDao {
    @Query("SELECT * FROM currencyroom")
    fun getCurrenciesList(): Single<List<CurrencyRoom>>

    @Update
    fun update(currencyRoom: CurrencyRoom):Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrencies(list: List<CurrencyRoom>)

    @Query("SELECT * FROM currencyroom WHERE isFav = 1")
    fun getFavorite() : Single<List<CurrencyRoom>>

}