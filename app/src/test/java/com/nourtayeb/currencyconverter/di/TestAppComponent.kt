package com.nourtayeb.currencyconverter.base


import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewholders.CurrencyViewHolder
import com.nourtayeb.currencyconverter.common.di.modules.*
import com.nourtayeb.currencyconverter.common.di.wrappers.ActivityWrapper
import com.nourtayeb.currencyconverter.di.modules.TestRxJavaModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, TestRxJavaModule::class, RepositoryModule::class,
    ViewModelModule::class, LocaleStorageModule::class, MapperModule::class])
interface TestAppComponent {

    fun inject(baseTest: BaseTest)


}