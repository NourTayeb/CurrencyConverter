package com.nourtayeb.currencyconverter.common.base.base_presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nourtayeb.currencyconverter.common.di.ViewModelFactory
import com.nourtayeb.currencyconverter.common.di.wrappers.ActivityWrapper

abstract class BaseActivity<ViewBinding:ViewDataBinding>: AppCompatActivity() {


    var viewModelFactory: ViewModelFactory
        get() = activityWrapper ?.viewModelFactory
        set(value) {}


    var activityWrapper = ActivityWrapper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDagger()
        setUpViewBindingAndViewModel()
    }


    private fun configureDagger() = (this.application as BaseApplication).appComponent.inject(activityWrapper)

    abstract fun getLayoutId():Int

    lateinit var binding: ViewBinding

    fun setUpViewBindingAndViewModel() {
        binding = DataBindingUtil.setContentView(this,getLayoutId())
        binding.lifecycleOwner = this
        setUpViewModel()
    }

    fun handleEvent(it : Event){
        when(it) {
            is ActivityStarter -> it.startActivity(this)
            is ActivityFinisher -> it.finish(this)
            is ToastMessage -> it.showToast(this)
        }
    }

    abstract fun setUpViewModel()

}