package com.example.flowerpower.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.flowerpower.R
import com.example.flowerpower.adapters.OrderListAdapter
import com.example.flowerpower.databinding.FragmentOrderlistBinding
import com.example.flowerpower.factory.ViewModelFactory
import com.example.flowerpower.repositories.OrderListRepository
import com.example.flowerpower.services.OrderListService
import com.example.flowerpower.viewmodel.OrderListViewModel

class OrderListFragment : Fragment() {

    companion object {
        private val TAG: String = OrderListFragment::class.java.canonicalName
        fun newInstance() = OrderListFragment
    }

    lateinit var viewModel: OrderListViewModel
    private val orderListService = OrderListService.getInstance()
    var binding: FragmentOrderlistBinding? = null
    private val adapter = OrderListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderlistBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelFactory(OrderListRepository(orderListService)))
            .get(OrderListViewModel::class.java)
        binding?.orderListRecycleView?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderListUpdateObserver()
    }

    private fun orderListUpdateObserver() {
        viewModel.orderList.observe(viewLifecycleOwner, {
            Log.d(TAG, getString(R.string.ORDER_LIST_UPDATED) + it + " size " + it.size)
            adapter.setOrderList(it)
        })
        viewModel.getOrderList()
    }
}