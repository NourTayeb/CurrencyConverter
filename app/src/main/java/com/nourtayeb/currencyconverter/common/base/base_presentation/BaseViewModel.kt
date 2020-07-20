package com.nourtayeb.currencyconverter.common.base.base_presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    //protected var disposables: CompositeDisposable = CompositeDisposable()

    var event: MutableLiveData<Event> = MutableLiveData()
//    override fun onCleared() {
//        this.disposables.dispose()
//        super.onCleared()
//    }
}