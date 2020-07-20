package com.nourtayeb.currencyconverter.clean_arch.data.local.db.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CurrencyRoom (@field:PrimaryKey val code:String, val name:String,val isFav:Boolean)
