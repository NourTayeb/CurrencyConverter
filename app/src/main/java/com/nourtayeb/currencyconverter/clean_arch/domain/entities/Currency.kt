package com.nourtayeb.currencyconverter.clean_arch.domain.entities


data class Currency (val code:String, val name:String,var isFav: Boolean = false,var rate : Double? = null){
    constructor():this("","")
    constructor(code:String):this(code,"")
    fun getRoundedCurrency() = String.format("%.2f", rate).toDouble()
}


