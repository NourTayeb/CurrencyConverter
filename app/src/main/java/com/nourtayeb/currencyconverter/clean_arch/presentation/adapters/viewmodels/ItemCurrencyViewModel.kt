package com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewmodels

import androidx.lifecycle.MutableLiveData
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseViewModel
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.usecases.AddCurrencyToFavoriteUseCase
import javax.inject.Inject


open class ItemCurrencyViewModel
    @Inject constructor(var addCurrencyToFavoriteUseCase:AddCurrencyToFavoriteUseCase):
    BaseViewModel() {
    var callback :(code:String) -> Unit = {}
    lateinit var currency: Currency
    val  name= MutableLiveData<String>()
    val  code= MutableLiveData<String>()
    val  rate= MutableLiveData<String>()
    val fav = MutableLiveData<Boolean>()
    val favVisibility = MutableLiveData<Boolean>()

    open fun toggleFav(){
        toggleFav(AddCurrencyToFavoriteUseCase.Params(currency))
    }
    open fun toggleFav(params:AddCurrencyToFavoriteUseCase.Params ) {
        currency.isFav = currency.isFav.not()
        fav.value = currency.isFav
        addCurrencyToFavoriteUseCase.execute(params =params)
    }

    open fun setAsDefaultCurrency() {
        callback(currency.code)
    }

    open fun bind(
        currency: Currency,
        defaultCurrencySelection: Boolean,
        callback :(code:String) -> Unit = {}
    ){
        this.currency=currency
        if(defaultCurrencySelection) this.callback=callback
        name.value = currency.name
        code.value = currency.code
        fav.value = currency.isFav
        rate.value = currency.rate?.let{ "${currency.getRoundedCurrency()}" }
        favVisibility.value = defaultCurrencySelection
    }
}
