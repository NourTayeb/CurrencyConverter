package com.nourtayeb.currencyconverter.common.extensions

fun <E> List<E>.toCommaSeperatedString() =
    this.joinToString(",")
