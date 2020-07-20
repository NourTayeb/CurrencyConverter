package com.nourtayeb.currencyconverter.common.di.modules

import com.nourtayeb.currencyconverter.clean_arch.data.mapper.ConvertionMapper
import com.nourtayeb.currencyconverter.clean_arch.data.mapper.CurrencyMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Provides
    @Singleton
    fun provideCurrencyMapper() = CurrencyMapper()

    @Provides
    @Singleton
    fun provideConvertionMapper() = ConvertionMapper()


}