package com.nourtayeb.currencyconverter.common.base.base_presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.nourtayeb.currencyconverter.R
import com.nourtayeb.currencyconverter.clean_arch.presentation.select_currency.FavoriteCurrenciesActivity
import com.nourtayeb.currencyconverter.common.extensions.putExtra




sealed class Event


// ----- ActivityStarter --- //
enum class ActivityName{
    FAVORITE_CURRENCIES
}
class ActivityStarter(val activityName: ActivityName, val extras:Map<String, Any?>? = null, val finishAfter:Boolean=false,
                      val forResult:Boolean =false ):
    Event(){

    fun startActivity(activity: FragmentActivity,resultCode:Int =0){
        val intent: Intent =
            when (activityName){
                ActivityName.FAVORITE_CURRENCIES -> Intent(activity,FavoriteCurrenciesActivity::class.java)
            }

        if(extras!=null){
            for ((key, value) in extras) {
                intent.putExtra(key,value)
            }
        }
        if(forResult) {
            activity.startActivityForResult(intent , resultCode)
        } else{
            activity.startActivity(intent)
        }
        if(finishAfter && activity is Activity){
            activity.finish()
        }
    }
}

// ----- ActivityFinisher --- //
class ActivityFinisher(val extras:Map<String, Any?>? = null):
    Event(){
    fun finish(context: Context){
        if(context is Activity){
            val returnIntent = Intent()
            if(extras!=null){
                for ((key, value) in extras) {
                    returnIntent.putExtra(key,value)
                }
                context.setResult(Activity.RESULT_OK, returnIntent)
            }
            context.finish()
        }
    }
}




// ----- ToastMessage --- //
class ToastMessage(val toastEvent: ToastEvent) : Event(){
    fun showToast(context: Context){
        when (toastEvent){
            ToastEvent.SET_DEFAULT_CURRENCY -> Toast.makeText(context,context.resources.getString(R.string.set_default_currency), Toast.LENGTH_LONG).show()
            ToastEvent.ERROR_LOADING_DATA -> Toast.makeText(context,context.resources.getString(R.string.error_loading_the_data), Toast.LENGTH_LONG).show()
            ToastEvent.ERROR_CONVERTING_DATA -> Toast.makeText(context,context.resources.getString(R.string.error_converting), Toast.LENGTH_LONG).show()
            ToastEvent.ENTER_AMOUNT -> Toast.makeText(context,context.resources.getString(R.string.enter_amount), Toast.LENGTH_LONG).show()
        }
    }
}
enum class ToastEvent{
    SET_DEFAULT_CURRENCY,ERROR_LOADING_DATA,ERROR_CONVERTING_DATA,ENTER_AMOUNT
}

