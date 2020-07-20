package com.nourtayeb.currencyconverter.clean_arch.data.mapper

import com.nourtayeb.currencyconverter.clean_arch.data.local.db.entities.CurrencyRoom
import com.nourtayeb.currencyconverter.clean_arch.domain.entities.Currency
import com.nourtayeb.currencyconverter.common.USD
import com.nourtayeb.currencyconverter.common.base.base_data.BaseMapperRepository


class ConvertionMapper :
    BaseMapperRepository<List<Currency>, List<CurrencyRoom>, Map<String, Double>> {
    override fun RoomToRetrofit(data: List<CurrencyRoom>): Map<String,Double> =
        TODO("not implemented")


    override fun RetrofitToRoom(data: Map<String,Double>): List<CurrencyRoom> {
        TODO("not implemented")
    }


    override fun RoomToDomain(data: List<CurrencyRoom>): List<Currency> {
        TODO("not implemented")
    }

    override fun DomainToRoom(domain: List<Currency>): List<CurrencyRoom> {
        TODO("not implemented")
    }

    override fun RetrofitToDomain(map: Map<String,Double>): List<Currency> {
        val mutableList = mutableListOf<Currency>()
        map?.forEach { (key, value) -> mutableList.add(Currency(key.replace(USD,"") ,key,rate = value)) }
        return mutableList
    }
    fun RetrofitToDomain(response: Map<String,Double>,list:List<Currency>): List<Currency> {
        val mutableList = mutableListOf<Currency>()
        val map = list.associateBy({it.code}, {it.name})
        response?.forEach { (key, value) ->
            if(map.get(key.replace(USD,""))!=null) {
                mutableList.add(
                    Currency(
                        key.replace(USD, ""),
                        map.get(key.replace(USD, ""))!!,
                        rate = value
                    )
                )
            }
        }
        return mutableList
    }

    override fun DomainToRetrofit(domain: List<Currency>): Map<String,Double> {
        TODO("not implemented")
    }


}