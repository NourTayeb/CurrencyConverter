package com.nourtayeb.currencyconverter.clean_arch.data.mapper

import com.nourtayeb.currencyconverter.common.extensions.fromObjectToMap
import com.nourtayeb.currencyconverter.clean_arch.data.local.db.entities.CurrencyRoom
import com.nourtayeb.currencyconverter.clean_arch.data.remote.responses.currencies.Currencies
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.common.base.base_data.BaseMapperRepository


class CurrencyMapper :
    BaseMapperRepository<List<Currency>, List<CurrencyRoom>, Currencies> {
    override fun RoomToRetrofit(data: List<CurrencyRoom>): Currencies =
        DomainToRetrofit(RoomToDomain(data))


    override fun RetrofitToRoom(data: Currencies): List<CurrencyRoom> =
        DomainToRoom(RetrofitToDomain(data))

    override fun RoomToDomain(data: List<CurrencyRoom>): List<Currency> {
        val mutableList : MutableList<Currency> = arrayListOf()
        data.forEach {  mutableList.add(Currency(it.code,it.name,it.isFav))}
        return mutableList
    }

    override fun DomainToRoom(domain: List<Currency>): List<CurrencyRoom> {
        val mutableList : MutableList<CurrencyRoom> = arrayListOf()
        domain.forEach {  mutableList.add(CurrencyRoom(it.code,it.name,it.isFav))}
        return mutableList
    }

    override fun RetrofitToDomain(data: Currencies): List<Currency> {
        val map= fromObjectToMap(data)
        val mutableList = mutableListOf<Currency>()
        map?.forEach { (key, value) -> mutableList.add(Currency(key as String,value as String)) }
        return mutableList
    }

    override fun DomainToRetrofit(domain: List<Currency>): Currencies {
        TODO("not implemented")
    }


    fun RoomToDomain(data: CurrencyRoom): Currency {
        return Currency(data.code,data.name,data.isFav)
    }

    fun DomainToRoom(domain: Currency): CurrencyRoom {
        return CurrencyRoom(domain.code,domain.name,domain.isFav)
    }

}
