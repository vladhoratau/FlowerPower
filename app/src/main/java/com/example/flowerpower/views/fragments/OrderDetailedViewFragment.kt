package com.example.flowerpower.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flowerpower.R
import com.example.flowerpower.adapters.OrderListAdapter
import com.example.flowerpower.databinding.FragmentOrderDetailedViewBinding
import com.example.flowerpower.models.Order

class OrderDetailedViewFragment : Fragment() {

    companion object {
        private val TAG: String = OrderDetailedViewFragment::class.java.canonicalName

        fun newInstance() : OrderDetailedViewFragment {
            return OrderDetailedViewFragment()
        }
    }

    var binding: FragmentOrderDetailedViewBinding? = null
    private lateinit var order: Order

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailedViewBinding.inflate(layoutInflater)
        order = arguments?.getSerializable(getString(R.string.PASSED_ORDER)) as Order
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.orderID?.text = OrderListAdapter.ORDER_ID_TEXT + order.orderID
        binding?.orderDescription?.text =
            getString(R.string.ORDER_DESCRIPTION_TEXT) + order.description
        binding?.orderPrice?.text = getString(R.string.ORDER_PRICE_TEXT) + order.price.toString()
        binding?.deliverTo?.text = getString(R.string.ORDER_DELIVER_TO_TEXT) + order.deliverTo
        binding?.orderStatus?.text = OrderListAdapter.ORDER_STATUS_TEXT + order.status
    }
}

