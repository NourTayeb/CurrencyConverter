package com.nourtayeb.currencyconverter.common.base.base_presentation

import androidx.lifecycle.MutableLiveData

abstract class BaseListViewModel<DomainEntity> :
    BaseViewModel(){
    val observableList: MutableLiveData<List<DomainEntity>> = MutableLiveData()

    abstract fun loadData()
    abstract fun showError()
    abstract fun showData(list:List<DomainEntity>)
}