package com.nourtayeb.currencyconverter.clean_arch.presentation.select_currency

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.nourtayeb.currencyconverter.base.BaseTest
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.usecases.AddCurrencyToFavoriteUseCase
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
class FavoriteCurrenciesViewModelIntegrationTest: BaseTest() {
    override fun isMockServerEnabled() = true


    override fun getMockContext(): Context = activity
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var activity: FragmentActivity


    @Inject
    lateinit var viewModel: FavoriteCurrenciesViewModel

    @Mock
    lateinit var getCurrenciesListUseCase: GetCurrenciesListUseCase

    @Before
    override fun setUp() {
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        super.setUp()
        MockitoAnnotations.initMocks(this)
        viewModel = ViewModelProviders.of(getMockContext() as FragmentActivity, viewModelFactory)[FavoriteCurrenciesViewModel::class.java]
    }


    @Test
    fun loadData_calls_getCurrenciesListUseCase_execute(){
        viewModel.getCurrenciesListUseCase = getCurrenciesListUseCase
        viewModel.loadData()
        Mockito.verify(getCurrenciesListUseCase).execute(
            ArgumentMatchers.any(),
            ArgumentMatchers.any(),
            ArgumentMatchers.any(),
            ArgumentMatchers.any(GetCurrenciesListUseCase.Params::class.java))
    }





}