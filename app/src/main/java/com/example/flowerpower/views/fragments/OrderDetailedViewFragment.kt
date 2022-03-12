package com.example.flowerpower.views.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flowerpower.R
import com.example.flowerpower.databinding.FragmentOrderDetailedViewBinding
import com.example.flowerpower.factory.ViewModelFactory
import com.example.flowerpower.models.Order
import com.example.flowerpower.models.Status
import com.example.flowerpower.utils.ApplicationClass
import com.example.flowerpower.utils.InternetUtils
import com.example.flowerpower.utils.ToastMessage
import com.example.flowerpower.viewmodel.DBOrderListViewModel
import com.google.android.material.textview.MaterialTextView

class OrderDetailedViewFragment : Fragment() {

    companion object {
        private val TAG: String = OrderDetailedViewFragment::class.java.canonicalName

        fun newInstance(): OrderDetailedViewFragment {
            return OrderDetailedViewFragment()
        }
    }

    var binding: FragmentOrderDetailedViewBinding? = null
    private val dbOrderListViewModel: DBOrderListViewModel by viewModels {
        ViewModelFactory()
    }
    private lateinit var order: Order

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailedViewBinding.inflate(layoutInflater)
        order = arguments?.getSerializable(getString(R.string.PASSED_ORDER)) as Order
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detailed_view_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTextView(ApplicationClass.instance.getString(R.string.ORDER_ID_TEXT) + order.orderID, binding?.orderID)
        updateTextView(
            ApplicationClass.instance.getString(R.string.ORDER_DESCRIPTION_TEXT) + order.description,
            binding?.orderDescription
        )
        updateTextView(
            ApplicationClass.instance.getString(R.string.ORDER_PRICE_TEXT) + order.price.toString(),
            binding?.orderPrice
        )
        updateTextView(
            ApplicationClass.instance.getString(R.string.ORDER_DELIVER_TO_TEXT) + order.deliverTo,
            binding?.deliverTo
        )
        updateTextView(ApplicationClass.instance.getString(R.string.ORDER_STATUS_TEXT) + order.status, binding?.status)

        if (order.status == Status.New) {
            binding?.changeStatusButton?.text = ApplicationClass.instance.getString(R.string.PENDING_ORDER)
        } else if (order.status == Status.Pending) {
            binding?.changeStatusButton?.text = ApplicationClass.instance.getString(R.string.DELIVERED_ORDER)
        }
        else {
            binding?.changeStatusButton?.visibility = View.GONE
        }

        binding?.changeStatusButton?.setOnClickListener {
            updateOrderStatus()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                if (InternetUtils.isInternetConnection(context)) {
                    val orderListFragment = OrderListFragment()
                    replaceFragment(orderListFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateOrderStatus() {
        when (order.status) {
            Status.New -> order.status = Status.Pending
            Status.Pending -> order.status = Status.Delivered
            null -> Status.New
        }
        order.status?.let { dbOrderListViewModel.updateOrderStatus(order.orderID, it) }
        updateTextView(ApplicationClass.instance.getString(R.string.ORDER_STATUS_TEXT) + order.status, binding?.status)
    }

    private fun updateTextView(text: String, view: MaterialTextView?) {
        view?.text = text
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}

