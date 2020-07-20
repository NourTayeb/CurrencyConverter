package com.nourtayeb.currencyconverter.common.di.components

import com.nourtayeb.currencyconverter.common.di.modules.*
import com.nourtayeb.currencyconverter.common.di.wrappers.ActivityWrapper
import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewholders.CurrencyViewHolder
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class,RxJavaModule::class, RepositoryModule::class,
    ViewModelModule::class, LocaleStorageModule::class,MapperModule::class])
interface AppComponent {

    fun inject(wrapper: ActivityWrapper)

    fun inject(viewHolder: CurrencyViewHolder)


}