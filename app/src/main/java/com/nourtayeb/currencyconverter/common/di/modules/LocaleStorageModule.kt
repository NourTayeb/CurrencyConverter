package com.nourtayeb.currencyconverter.common.di.modules

import android.content.Context
import androidx.room.Room
import com.nourtayeb.currencyconverter.clean_arch.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocaleStorageModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDatabse() = Room.databaseBuilder(context, AppDatabase::class.java, "currencies").build()
}