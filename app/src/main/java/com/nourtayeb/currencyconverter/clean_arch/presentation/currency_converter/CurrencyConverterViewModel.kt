package com.nourtayeb.currencyconverter.clean_arch.presentation.currency_converter

import androidx.lifecycle.MutableLiveData
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.usecases.ConvertCurrencyUseCase
import com.nourtayeb.currencyconverter.clean_arch.domain.usecases.GetFavoriteCurrenciesUseCase
import com.nourtayeb.currencyconverter.common.REQUEST_FOR_DEFAULT_CURRENCY
import com.nourtayeb.currencyconverter.common.SUPPORTED_CURRENCIES
import com.nourtayeb.currencyconverter.common.USD
import com.nourtayeb.currencyconverter.common.base.base_presentation.*
import com.nourtayeb.currencyconverter.common.extensions.notifyObservers
import javax.inject.Inject

open class CurrencyConverterViewModel @Inject
constructor(
    private val getFavoriteCurrencies: GetFavoriteCurrenciesUseCase
    , private val convertCurrencies: ConvertCurrencyUseCase
) : BaseListViewModel<Currency>() {

    val amount: MutableLiveData<String> = MutableLiveData()
    val currency: MutableLiveData<String> = MutableLiveData()
    val noData: MutableLiveData<Boolean> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()


    override fun loadData() {
        getFavoriteCurrencies.execute(
            onSuccess = {
                showData(it)
            }
            , onError = {
                showError()
            },
            params = GetFavoriteCurrenciesUseCase.Params()
        )
    }


    override fun onCleared() {
        getFavoriteCurrencies.dispose()
        convertCurrencies.dispose()
        super.onCleared()
    }

    override fun showError() {
        event.value =
            ToastMessage(ToastEvent.SET_DEFAULT_CURRENCY)
    }


    override fun showData(it: List<Currency>) {
        noData.value = it.isEmpty()
        observableList.value = it
    }


    open fun convert() {
        if (currency.value != null && !currency.value.equals("")) {
            loading.value = true
            convertCurrencies.execute(
                onSuccess = {
                    showConvertedCurrencies(it)
                }, onError = {
                    showErrorConverting()
                },
                onFinished = {
                    loading.value = false
                },
                params = ConvertCurrencyUseCase.Params(USD, observableList.value!!)
            )
        } else {
            goToCurrencySelector()
        }
    }

    open fun showErrorConverting() {
        event.value =
            ToastMessage(ToastEvent.ERROR_CONVERTING_DATA)
    }

    open fun showConvertedCurrencies(list: List<Currency>) {
        if (amount.value != null && amount.value != null) {
            observableList.value?.forEachIndexed { index, curr ->
                if (currency.value != null && SUPPORTED_CURRENCIES.get(currency.value!!) != null) {
                    curr.rate =
                        list[index].rate?.times(SUPPORTED_CURRENCIES.get(currency.value!!)!! * amount.value?.toDouble()!!)
                }
            }
            observableList.notifyObservers()
        } else {
            event.value = ToastMessage(ToastEvent.ENTER_AMOUNT)
        }
    }

    fun goToCurrencySelector() {
        event.value = ActivityStarter(
            ActivityName.FAVORITE_CURRENCIES,
            forResult = true,
            extras = mapOf(REQUEST_FOR_DEFAULT_CURRENCY to true)
        )
        event.value =
            ToastMessage(ToastEvent.SET_DEFAULT_CURRENCY)
    }

    fun setDefaultCurrency(code: String) {
        currency.value = code
    }

    fun goToFavorite() {
        event.value = ActivityStarter(
            ActivityName.FAVORITE_CURRENCIES,
            forResult = true
        )
    }

}