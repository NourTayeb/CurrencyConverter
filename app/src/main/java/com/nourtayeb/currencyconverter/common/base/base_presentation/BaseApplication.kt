package com.nourtayeb.currencyconverter.common.base.base_presentation

import android.app.Application
import com.nourtayeb.currencyconverter.common.API_BASE_URL
import com.nourtayeb.currencyconverter.common.di.components.AppComponent
import com.nourtayeb.currencyconverter.common.di.components.DaggerAppComponent
import com.nourtayeb.currencyconverter.common.di.modules.*

open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = this.initDagger()
    }
    protected open fun initDagger(): AppComponent
            = DaggerAppComponent.builder()
            .netModule(NetModule(API_BASE_URL,this))
            .mapperModule(MapperModule())
            .localeStorageModule(LocaleStorageModule(this))
            .repositoryModule(RepositoryModule())
            .rxJavaModule(RxJavaModule())
            .build()
}