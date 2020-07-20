package com.nourtayeb.currencyconverter.common.base.base_presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nourtayeb.currencyconverter.common.di.components.AppComponent
import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewholders.CurrencyViewHolder

open class BaseViewHolder(val view:View) : RecyclerView.ViewHolder(view){
    var appComponent: AppComponent = (view.context.applicationContext as BaseApplication).appComponent
    init {
        when (this) {
            is CurrencyViewHolder -> appComponent.inject(this)
        }
    }

}