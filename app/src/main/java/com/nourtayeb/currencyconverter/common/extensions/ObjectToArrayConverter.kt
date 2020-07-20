package com.nourtayeb.currencyconverter.common.extensions

import com.google.gson.Gson

var gson = Gson()
fun <T>toJsonString(obj:T):String{
    return  gson.toJson(obj)
}

fun convertToMap(string:String):Map<*,*>?{
    return gson.fromJson(string, Map::class.java)
}

fun <T>fromObjectToMap(obj:T):Map<*,*>?{
    val str:String= toJsonString<T>(obj)
    return convertToMap(str)
}




