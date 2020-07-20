package com.nourtayeb.currencyconverter.common.base.base_presentation
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter <DomainEntity,VH:RecyclerView.ViewHolder>(
    val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<VH>(), Observer<List<DomainEntity>?> {

    var onItemClick:(Any)->Unit ={}



    protected lateinit var list:List<DomainEntity>

    override fun getItemCount(): Int {
        return if(::list.isInitialized) list.size else 0
    }

    override fun onChanged(t: List<DomainEntity>?) {
        if (t != null) {
            list = t
            notifyDataSetChanged()
        }
    }



}