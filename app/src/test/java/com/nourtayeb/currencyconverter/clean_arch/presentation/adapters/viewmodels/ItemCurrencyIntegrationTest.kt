package com.nourtayeb.currencyconverter.clean_arch.presentation.adapters.viewmodels

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.nourtayeb.currencyconverter.base.BaseTest
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.usecases.AddCurrencyToFavoriteUseCase
import com.nourtayeb.currencyconverter.clean_arch.presentation.currency_converter.CurrencyConverterViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P ])
class ItemCurrencyIntegrationTest: BaseTest() {
    override fun isMockServerEnabled() = false


    override fun getMockContext(): Context = activity
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var activity: FragmentActivity


    @Inject
    lateinit var itemCurrencyViewModel: ItemCurrencyViewModel

    @Mock
    lateinit var addCurrencyToFavoriteUseCase:AddCurrencyToFavoriteUseCase

    @Before
    override fun setUp() {
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        super.setUp()
        MockitoAnnotations.initMocks(this)
        itemCurrencyViewModel = ViewModelProviders.of(getMockContext() as FragmentActivity, viewModelFactory)[ItemCurrencyViewModel::class.java]
    }
    @Test
    fun toggleFav_calls_addCurrencyToFavoriteUseCase_execute(){
        val curr = Currency()
        val params = AddCurrencyToFavoriteUseCase.Params(curr)
        itemCurrencyViewModel.addCurrencyToFavoriteUseCase = addCurrencyToFavoriteUseCase
        itemCurrencyViewModel.currency = curr
        itemCurrencyViewModel.toggleFav(params)
        Mockito.verify(addCurrencyToFavoriteUseCase).execute(params = params )
    }

}