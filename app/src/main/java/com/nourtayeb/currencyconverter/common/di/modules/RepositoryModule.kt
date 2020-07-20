package com.nourtayeb.currencyconverter.common.di.modules

import com.nourtayeb.currencyconverter.clean_arch.data.mapper.CurrencyMapper
import com.nourtayeb.currencyconverter.clean_arch.data.local.db.AppDatabase
import com.nourtayeb.currencyconverter.clean_arch.data.mapper.ConvertionMapper
import com.nourtayeb.currencyconverter.clean_arch.data.remote.Api
import com.nourtayeb.currencyconverter.clean_arch.data.repositories.CurrencyRepository
import com.nourtayeb.currencyconverter.clean_arch.domain.repositories.CurrenciesRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun list(api: Api,database: AppDatabase,currencyMapper:CurrencyMapper,convertionMapper: ConvertionMapper): CurrenciesRepository
            = CurrencyRepository(database,api,currencyMapper,convertionMapper)



}