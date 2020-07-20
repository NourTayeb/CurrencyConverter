package com.nourtayeb.currencyconverter.clean_arch.data.local.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.nourtayeb.currencyconverter.clean_arch.data.local.db.entities.CurrencyRoom


@Database(entities = [CurrencyRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currenciesDao(): CurrenciesDao
}