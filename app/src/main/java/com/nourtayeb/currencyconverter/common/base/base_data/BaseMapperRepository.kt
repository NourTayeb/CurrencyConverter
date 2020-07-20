package com.nourtayeb.currencyconverter.common.base.base_data

interface BaseMapperRepository<Domain, ROOM, RETROFIT> {

    fun RoomToDomain(data: ROOM):Domain
    fun RetrofitToDomain(data: RETROFIT):Domain

    fun DomainToRoom(domain: Domain): ROOM
    fun DomainToRetrofit(domain: Domain): RETROFIT

    fun RoomToRetrofit(data:ROOM):RETROFIT
    fun RetrofitToRoom(data:RETROFIT):ROOM
}
