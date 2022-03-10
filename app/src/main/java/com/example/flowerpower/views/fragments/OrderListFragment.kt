package com.example.flowerpower.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.flowerpower.R
import com.example.flowerpower.adapters.OrderListAdapter
import com.example.flowerpower.databinding.FragmentOrderlistBinding
import com.example.flowerpower.factory.ViewModelFactory
import com.example.flowerpower.models.Order
import com.example.flowerpower.utils.InternetUtils
import com.example.flowerpower.viewmodel.DBOrderListViewModel
import com.example.flowerpower.viewmodel.OrderListViewModel

class OrderListFragment : Fragment(), OrderListAdapter.OnItemClickListener {

    companion object {
        private val TAG: String? = OrderListFragment::class.java.canonicalName

        fun newInstance(): OrderListFragment {
            return OrderListFragment()
        }
    }

    private val orderListViewModel: OrderListViewModel by viewModels {
        ViewModelFactory()
    }
    private val dbOrderListViewModel: DBOrderListViewModel by viewModels {
        ViewModelFactory()
    }
    var binding: FragmentOrderlistBinding? = null
    private var adapter = OrderListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderlistBinding.inflate(layoutInflater)
        binding?.orderListRecycleView?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderListUpdateObserver()
    }

    override fun onItemClick(order: Order) {
        val bundle = Bundle()
        bundle.putSerializable(getString(R.string.PASSED_ORDER), order)
        val transaction = this.parentFragmentManager.beginTransaction()
        val orderListFragment = OrderDetailedViewFragment.newInstance()
        orderListFragment.arguments = bundle
        transaction.replace(R.id.fragmentContainer, orderListFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun orderListUpdateObserver() {
        if (InternetUtils.isInternetConnection(context)) {
            Log.d("Vlad", "Internet")
            orderListViewModel.orderList.observe(viewLifecycleOwner, {
                Log.d(TAG, getString(R.string.ORDER_LIST_UPDATED) + it + " size " + it.size)
                adapter.setOrderList(it)
            })
            orderListViewModel.getOrderList()
        } else {
            getDBOrderList()
            Toast.makeText(context, getString(R.string.NO_INTERNET), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDBOrderList() {
        dbOrderListViewModel.getAllDBOrders().observe(this, Observer {
            if (it.isNotEmpty()) {
                for (item in it)
                    adapter.setOrderList(it)
            }
        })
    }
}