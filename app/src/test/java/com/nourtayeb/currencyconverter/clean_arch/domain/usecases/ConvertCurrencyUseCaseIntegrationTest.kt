package com.nourtayeb.currencyconverter.clean_arch.domain.usecases

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import com.nourtayeb.currencyconverter.base.BaseTest
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.repositories.CurrenciesRepository
import com.nourtayeb.currencyconverter.common.base.base_domain.SingleUseCase
import com.nourtayeb.currencyconverter.common.di.modules.OBSERVER_ON
import com.nourtayeb.currencyconverter.common.di.modules.SUBCRIBER_ON
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Named

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P ])
class ConvertCurrencyUseCaseIntegrationTest: BaseTest() {
    override fun isMockServerEnabled() = false

    override fun getMockContext(): Context = activity
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var activity: FragmentActivity


    lateinit var convertCurrencyUseCase:ConvertCurrencyUseCase


    @Mock
    lateinit var currencyRepository: CurrenciesRepository

    @Before
    override fun setUp() {
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        super.setUp()
        MockitoAnnotations.initMocks(this)
        //TODO : use injection instead
        convertCurrencyUseCase = ConvertCurrencyUseCase(currencyRepository,
            AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread())
    }

    @Test
    fun buildUseCaseSingle_calls_currencyRepository_addCurrencyToFav_only(){
        val default= "USD"
        val list= listOf<Currency>()
        Mockito.`when`(currencyRepository.convertCurrencies(default,list)).thenReturn(Single.just(list))
        convertCurrencyUseCase.buildUseCaseSingle(ConvertCurrencyUseCase.Params(default,list))
        Mockito.verify(currencyRepository).convertCurrencies(default,list)
    }




}