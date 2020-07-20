package com.nourtayeb.currencyconverter.clean_arch.domain.entities

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CurrencyUnitTest {
    lateinit var currency:Currency
    @Before
    fun setUp(){
        currency= Currency("","",rate = 0.0)
    }
    @Test
    fun zero_rate_returns_format() {
        assertEquals(currency.getRoundedCurrency(), 0.0,0.0)
    }
    @Test
    fun one_digit_after_fp_rate_returns_format_1f() {
        currency.rate = 1.1
        assertEquals(currency.getRoundedCurrency(), 1.1,0.0)
    }
    @Test
    fun two_digit_after_fp_rate_returns_format_2f() {
        currency.rate = 1.12
        assertEquals(currency.getRoundedCurrency(), 1.12,0.0)
    }
    @Test
    fun three_digit_after_fp_rate_returns_format_2f() {
        currency.rate = 1.123
        assertEquals(currency.getRoundedCurrency(), 1.12,0.0)
    }
}
