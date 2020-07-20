package com.nourtayeb.currencyconverter.clean_arch.presentation.currency_converter

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.nourtayeb.currencyconverter.base.BaseTest
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.usecases.GetCurrenciesListUseCase
import org.hamcrest.Matchers
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
import java.net.HttpURLConnection
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P ])
class CurrencyConverterViewModelViewModelIntegrationTest: BaseTest() {
    override fun isMockServerEnabled() = true


    override fun getMockContext(): Context = activity
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var activity: FragmentActivity


    @Inject
    lateinit var viewModel: CurrencyConverterViewModel


    @Before
    override fun setUp() {
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        super.setUp()
        MockitoAnnotations.initMocks(this)
        viewModel = ViewModelProviders.of(getMockContext() as FragmentActivity, viewModelFactory)[CurrencyConverterViewModel::class.java]
    }


    @Test
    fun success_currencies_calls_showConvertedCurrencies() {
        mockHttpResponse("success_convert.json", HttpURLConnection.HTTP_OK)
        val spyViewModel=  Mockito.spy(viewModel)
        spyViewModel.currency.value = "USD"
        spyViewModel.observableList.value = listOf(Currency("EUR"),Currency("GBP"),Currency("CAD"),Currency("PLN"))
        spyViewModel.convert()
        Mockito.verify(spyViewModel).showConvertedCurrencies(ArgumentMatchers.anyListOf(Currency::class.java))
    }
    @Test
    fun success_currencies_never_calls_showErrorConverting() {
        mockHttpResponse("success_convert.json", HttpURLConnection.HTTP_OK)
        val spyViewModel=  Mockito.spy(viewModel)
        spyViewModel.currency.value = "USD"
        spyViewModel.observableList.value = listOf(Currency("EUR"),Currency("GBP"),Currency("CAD"),Currency("PLN"))
        spyViewModel.convert()
        Mockito.verify(spyViewModel, Mockito.never()).showErrorConverting()
    }



    @Test
    fun failed_currencies_calls_never_calls_showConvertedCurrencies() {
        mockHttpResponse("fail_convert.json", HttpURLConnection.HTTP_OK)
        val spyViewModel=  Mockito.spy(viewModel)
        spyViewModel.currency.value = "USD"
        spyViewModel.observableList.value = listOf(Currency("EUR"),Currency("GBP"),Currency("CAD"),Currency("PLN"))
        spyViewModel.convert()
        Mockito.verify(spyViewModel, Mockito.never()).showConvertedCurrencies(ArgumentMatchers.anyListOf(Currency::class.java))
    }
    @Test
    fun failed_currencies_calls_calls_showErrorConverting() {
        mockHttpResponse("fail_convert.json", HttpURLConnection.HTTP_OK)
        val spyViewModel=  Mockito.spy(viewModel)
        spyViewModel.currency.value = "USD"
        spyViewModel.observableList.value = listOf(Currency("EUR"),Currency("GBP"),Currency("CAD"),Currency("PLN"))
        spyViewModel.convert()
        Mockito.verify(spyViewModel).showErrorConverting()
    }




}