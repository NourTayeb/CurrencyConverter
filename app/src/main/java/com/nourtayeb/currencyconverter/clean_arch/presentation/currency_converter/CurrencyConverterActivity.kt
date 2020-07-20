package com.nourtayeb.currencyconverter.clean_arch.presentation.currency_converter

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nourtayeb.currencyconverter.R
import com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.ConvertedCurrenciesRecyclerViewAdapter
import com.nourtayeb.currencyconverter.common.base.base_presentation.ActivityStarter
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseActivity
import com.nourtayeb.currencyconverter.common.base.base_presentation.recyclerview_delegate.RecyclerViewDelegate
import com.nourtayeb.currencyconverter.common.base.base_presentation.recyclerview_delegate.RecyclerViewDelegateImp
import com.nourtayeb.currencyconverter.databinding.ActivityConverterBinding
import android.app.Activity
import com.nourtayeb.currencyconverter.common.DEFAULT_CURRENCY


class CurrencyConverterActivity : BaseActivity<ActivityConverterBinding>(),
    RecyclerViewDelegate<ConvertedCurrenciesRecyclerViewAdapter> by RecyclerViewDelegateImp() {

    val SELECT_DEFAULT_RESULT_CODE : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpRecyclerView(
            this,
            binding.recyclerView,
            ConvertedCurrenciesRecyclerViewAdapter(
                this
            ),
            binding.viewModel!!,
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL))

        with(binding.viewModel!!){
            loadData()
            event.observe(this@CurrencyConverterActivity, Observer {
                if(it is ActivityStarter && it.forResult){
                    it.startActivity(this@CurrencyConverterActivity,SELECT_DEFAULT_RESULT_CODE)
                } else {
                    handleEvent(it)
                }
            })
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === SELECT_DEFAULT_RESULT_CODE) {
            if (resultCode === Activity.RESULT_OK) {
                with(binding.viewModel!!){
                    data?.let {
                        val code=data.getStringExtra(DEFAULT_CURRENCY)
                        if(!code.equals("")){
                            setDefaultCurrency(code)
                        }else{
                            loadData()
                        }
                    }
                }
            }
        }
    }

    override fun setUpViewModel() {
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)[CurrencyConverterViewModel::class.java]
    }

    override fun getLayoutId() = R.layout.activity_converter

}