package com.nourtayeb.currencyconverter.clean_arch.presentation.select_currency

import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.usecases.GetCurrenciesListUseCase
import com.nourtayeb.currencyconverter.common.DEFAULT_CURRENCY
import com.nourtayeb.currencyconverter.common.SUPPORTED_CURRENCIES
import com.nourtayeb.currencyconverter.common.base.base_presentation.ActivityFinisher
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseListViewModel
import com.nourtayeb.currencyconverter.common.base.base_presentation.ToastEvent
import com.nourtayeb.currencyconverter.common.base.base_presentation.ToastMessage
import javax.inject.Inject

open class FavoriteCurrenciesViewModel @Inject constructor(var getCurrenciesListUseCase: GetCurrenciesListUseCase) :
    BaseListViewModel<Currency>() {

    var defaultCurrencySelection = false


    override fun loadData() {
        getCurrenciesListUseCase.execute(
            onSuccess = {
                showData(it)
            }
            , onError = {
                showError()
            },
            params = GetCurrenciesListUseCase.Params()
        )
    }


    override fun showError() {
        event.value =
            ToastMessage(ToastEvent.SET_DEFAULT_CURRENCY)
    }

    override fun showData(it: List<Currency>) {
        list = it
        observableList.value = if (defaultCurrencySelection)
            it.filter { it.code in SUPPORTED_CURRENCIES.keys }
        else it
    }


    lateinit var list: List<Currency>
    fun onFilterTextChange(s: CharSequence, start: Int, before: Int, count: Int) {
        if (this::list.isInitialized) {
            observableList.value = list.filter {
                it.code.toLowerCase().contains(s.toString().toLowerCase())
                        || it.name.toLowerCase().contains(s.toString().toLowerCase())
            }
        }
    }

    override fun onCleared() {
        getCurrenciesListUseCase.dispose()
        super.onCleared()
    }


    fun goBack() {
        event.value = ActivityFinisher(
            mapOf(DEFAULT_CURRENCY to "")
        )
    }

    fun setDefaultCurrency(code: String) {
        event.value = ActivityFinisher(
            mapOf(DEFAULT_CURRENCY to code)
        )
    }
}