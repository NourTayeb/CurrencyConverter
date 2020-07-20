package com.nourtayeb.currencyconverter.common.di.wrappers


import com.nourtayeb.currencyconverter.common.di.ViewModelFactory
import javax.inject.Inject


class ActivityWrapper {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

}