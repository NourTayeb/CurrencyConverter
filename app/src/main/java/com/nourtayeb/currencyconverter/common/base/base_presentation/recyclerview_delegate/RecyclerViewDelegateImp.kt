package com.nourtayeb.currencyconverter.common.base.base_presentation.recyclerview_delegate

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseListViewModel
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseRecyclerViewAdapter

class RecyclerViewDelegateImp<RecyclerViewAdapter: BaseRecyclerViewAdapter<*, *>>  :RecyclerViewDelegate<RecyclerViewAdapter>{
    override var adapter:RecyclerViewAdapter? = null
    override fun setUpRecyclerView(
        fragmentActivity: FragmentActivity,
        recyclerView: RecyclerView,
        adapter: RecyclerViewAdapter,
        viewModel: BaseListViewModel<*>,
        layoutManager: RecyclerView.LayoutManager
    ) {
        this.adapter = adapter
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.adapter = adapter
        recyclerView.setNestedScrollingEnabled(false)
        if(adapter is Observer<*>){
            viewModel.observableList.observe(fragmentActivity,adapter as Observer<List<*>>)
        }

    }
}