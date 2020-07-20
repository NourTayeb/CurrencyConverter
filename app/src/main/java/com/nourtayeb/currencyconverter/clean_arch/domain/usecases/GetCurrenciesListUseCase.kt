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

open class GetCurrenciesListUseCase @Inject constructor(private val currencyRepository: CurrenciesRepository,
                                                        @param:Named(SUBCRIBER_ON) private val subscriberOn: Scheduler,
                                                        @param:Named(OBSERVER_ON) private val observerOn: Scheduler
) :
    SingleUseCase<List<Currency>, GetCurrenciesListUseCase.Params>(subscriberOn, observerOn) {

    override fun buildUseCaseSingle(params: Params): Single<List<Currency>> {
        return currencyRepository.getCurrencies()
    }

    class Params (
        //val access_key: String
    )
}