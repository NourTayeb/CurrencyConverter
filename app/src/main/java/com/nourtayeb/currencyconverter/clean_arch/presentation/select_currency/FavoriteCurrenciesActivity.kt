package com.nourtayeb.currencyconverter.clean_arch.presentation.select_currency

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nourtayeb.currencyconverter.R
import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.FavoriteCurrenciesRecyclerViewAdapter
import com.nourtayeb.currencyconverter.common.REQUEST_FOR_DEFAULT_CURRENCY
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseActivity
import com.nourtayeb.currencyconverter.common.base.base_presentation.recyclerview_delegate.RecyclerViewDelegate
import com.nourtayeb.currencyconverter.common.base.base_presentation.recyclerview_delegate.RecyclerViewDelegateImp
import com.nourtayeb.currencyconverter.databinding.ActivityFavoriteCurrenciesBinding


class FavoriteCurrenciesActivity :
    BaseActivity<ActivityFavoriteCurrenciesBinding>(),
    RecyclerViewDelegate<FavoriteCurrenciesRecyclerViewAdapter> by RecyclerViewDelegateImp(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestForDefaultCurrency=intent.getBooleanExtra(REQUEST_FOR_DEFAULT_CURRENCY,false)
        setUpRecyclerView(
            this,
            binding.recyclerView,
            FavoriteCurrenciesRecyclerViewAdapter(this,requestForDefaultCurrency),
            binding.viewModel!!
        )
        with(binding.viewModel!!){
            defaultCurrencySelection =requestForDefaultCurrency
            loadData()
            event.observe(this@FavoriteCurrenciesActivity, Observer {
                handleEvent(it)
            })
        }
        adapter?.onItemClick = {
            if(it is String) binding.viewModel!!.setDefaultCurrency(it)
        }
    }


    override fun setUpViewModel() {
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)[FavoriteCurrenciesViewModel::class.java]
    }

    override fun onBackPressed() {
        with(binding.viewModel!!) { goBack() }
    }
    override fun getLayoutId() = R.layout.activity_favorite_currencies

}