package com.nourtayeb.currencyconverter.common.base.base_domain

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCase<T, in Params>(
    private val subscriberOn: Scheduler,
    private val observerOn: Scheduler
) : UseCase() {

    internal abstract fun buildUseCaseSingle(params: Params): Single<T>

    open fun execute(
        onSuccess: ((t: T) -> Unit)? = {},
        onError: ((t: Throwable) -> Unit)? = {},
        onFinished: (() -> Unit)? = {} ,
        params: Params?
    ) {
        disposeLast()
        if(params!=null) {
            lastDisposable = buildUseCaseSingle(params)
                .subscribeOn(subscriberOn)
                .observeOn(observerOn)
                .doAfterTerminate(onFinished)
                .subscribe(onSuccess, onError)
            lastDisposable?.let {
                compositeDisposable.add(it)
            }
        }
    }
}