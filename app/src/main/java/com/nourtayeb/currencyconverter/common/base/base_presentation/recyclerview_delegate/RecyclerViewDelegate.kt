package com.nourtayeb.currencyconverter.common.base.base_presentation.recyclerview_delegate

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseListViewModel
import com.nourtayeb.currencyconverter.common.base.base_presentation.BaseRecyclerViewAdapter

interface RecyclerViewDelegate<RecyclerViewAdapter: BaseRecyclerViewAdapter<*, *>> {
    var adapter:RecyclerViewAdapter?
    fun  setUpRecyclerView(
        fragmentActivity: FragmentActivity,
        recyclerView: RecyclerView,
        adapter:RecyclerViewAdapter,
        viewModel: BaseListViewModel<*>,
        layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false)
    )
}