package com.nourtayeb.currencyconverter.clean_arch.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner

import com.nourtayeb.currencyconverter.R
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseRecyclerViewAdapter
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewholders.CurrencyViewHolder
import com.nourtayeb.currencyconverter.databinding.ItemFavoriteBinding

class FavoriteCurrenciesRecyclerViewAdapter(
lifecycleOwner: LifecycleOwner,
var defaultCurrencySelection:Boolean =true
) : BaseRecyclerViewAdapter<Currency, CurrencyViewHolder>(lifecycleOwner){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding: ItemFavoriteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_favorite, parent, false)
        return CurrencyViewHolder(
            lifecycleOwner ,
            binding
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(list[position],defaultCurrencySelection,onItemClick)
    }

}