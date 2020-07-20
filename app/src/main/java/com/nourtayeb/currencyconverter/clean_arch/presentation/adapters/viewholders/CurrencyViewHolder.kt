package com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewholders


import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import androidx.lifecycle.Observer
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseViewHolder
import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewmodels.ItemCurrencyViewModel
import com.nourtayeb.currencyconverter.databinding.ItemConvertedBinding
import com.nourtayeb.currencyconverter.databinding.ItemFavoriteBinding
import javax.inject.Inject


class CurrencyViewHolder(private val lifecycleOwner: LifecycleOwner, private val binding: ViewDataBinding) :


    BaseViewHolder(binding.root) {
    @Inject
    lateinit var viewModel: ItemCurrencyViewModel


    fun bind(
        currency: Currency,
        defaultCurrencySelection: Boolean = false,
        onItemClick: (Any) -> Unit
    ){
        when (binding){
            is ItemFavoriteBinding -> binding.viewModel = viewModel
            is ItemConvertedBinding -> binding.viewModel = viewModel
        }
        viewModel.bind(currency,defaultCurrencySelection,onItemClick)
        binding.lifecycleOwner = lifecycleOwner
        viewModel.event.observe(lifecycleOwner, Observer {

        })
    }

}
