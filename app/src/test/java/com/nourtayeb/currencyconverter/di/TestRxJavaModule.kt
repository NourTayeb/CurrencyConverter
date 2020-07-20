package com.nourtayeb.currencyconverter.di.modules

import com.nourtayeb.currencyconverter.common.di.modules.OBSERVER_ON
import com.nourtayeb.currencyconverter.common.di.modules.SUBCRIBER_ON
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestRxJavaModule {

    @Provides
    @Named(SUBCRIBER_ON)
    @Singleton
    fun provideSubcriberOn(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(OBSERVER_ON)
    @Singleton
    fun provideObserverOn(): Scheduler = AndroidSchedulers.mainThread()
}