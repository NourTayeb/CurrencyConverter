package com.nourtayeb.currencyconverter.common.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewmodels.ItemCurrencyViewModel
import com.nourtayeb.currencyconverter.clean_arch.presentation.currency_converter.CurrencyConverterViewModel
import com.nourtayeb.currencyconverter.common.di.ViewModelFactory
import com.nourtayeb.currencyconverter.common.di.ViewModelKey
import com.nourtayeb.currencyconverter.clean_arch.presentation.select_currency.FavoriteCurrenciesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule(
) {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(FavoriteCurrenciesViewModel::class)
    internal abstract fun selector(viewModel: FavoriteCurrenciesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyConverterViewModel::class)
    internal abstract fun converter(viewModel: CurrencyConverterViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ItemCurrencyViewModel::class)
    internal abstract fun itemCurr(viewModel: ItemCurrencyViewModel): ViewModel




}