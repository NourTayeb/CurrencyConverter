package com.nourtayeb.currencyconverter.clean_arch.domain.usecases

import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.clean_arch.domain.repositories.CurrenciesRepository
import com.nourtayeb.currencyconverter.common.base.base_domain.SingleUseCase
import com.nourtayeb.currencyconverter.common.di.modules.OBSERVER_ON
import com.nourtayeb.currencyconverter.common.di.modules.SUBCRIBER_ON
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

open class ConvertCurrencyUseCase @Inject constructor(private val currencyRepository: CurrenciesRepository,
                                                      @param:Named(SUBCRIBER_ON) private val subscriberOn: Scheduler,
                                                      @param:Named(OBSERVER_ON) private val observerOn: Scheduler
) :
    SingleUseCase<List<Currency>, ConvertCurrencyUseCase.Params>(subscriberOn, observerOn) {

    override fun buildUseCaseSingle(params: Params): Single<List<Currency>> {
        return currencyRepository.convertCurrencies(params.source,params.currencies)
    }

    class Params(
        val source: String,
        val currencies:List<Currency>
    )
}