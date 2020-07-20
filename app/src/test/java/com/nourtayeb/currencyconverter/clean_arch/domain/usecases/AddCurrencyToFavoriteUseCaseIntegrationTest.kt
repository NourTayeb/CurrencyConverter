package com.nourtayeb.currencyconverter.clean_arch.domain.usecases

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import com.nourtayeb.currencyconverter.base.BaseTest
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.repositories.CurrenciesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleJust
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

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P ])
class AddCurrencyToFavoriteUseCaseIntegrationTest: BaseTest() {
    override fun isMockServerEnabled() = false

    override fun getMockContext(): Context = activity
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var activity: FragmentActivity


    lateinit var addCurrencyToFavoriteUseCase:AddCurrencyToFavoriteUseCase


    @Mock
    lateinit var currencyRepository: CurrenciesRepository

    @Before
    override fun setUp() {
        activity = Robolectric.setupActivity(FragmentActivity::class.java)
        super.setUp()
        MockitoAnnotations.initMocks(this)
        //TODO : use injection instead
        addCurrencyToFavoriteUseCase = AddCurrencyToFavoriteUseCase(currencyRepository,
            AndroidSchedulers.mainThread(),AndroidSchedulers.mainThread())
    }

    @Test
    fun buildUseCaseSingle_calls_currencyRepository_addCurrencyToFav_only(){
        val currency = Currency()
        Mockito.`when`(currencyRepository.addCurrencyToFav(currency)).thenReturn(Single.just(0))
        addCurrencyToFavoriteUseCase.buildUseCaseSingle(AddCurrencyToFavoriteUseCase.Params(currency))
        Mockito.verify(currencyRepository).addCurrencyToFav(currency)
    }




}