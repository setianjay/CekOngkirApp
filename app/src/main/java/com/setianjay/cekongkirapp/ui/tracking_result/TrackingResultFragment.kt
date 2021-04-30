package com.setianjay.cekongkirapp.ui.tracking_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentTrackingResultBinding
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.ui.tracking.TrackingViewModel
import timber.log.Timber

class TrackingResultFragment : Fragment() {
    private lateinit var binding: FragmentTrackingResultBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }
    private val wayBill by lazy { requireArguments().getString("waybill") }
    private val courier by lazy { requireArguments().getString("courier") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrackingResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserve()
        initListener()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchWayBill(wayBill!!, courier!!)
    }

    private fun setupObserve() {
        viewModel.wayBillResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    loadingWayBill(true)
                    Timber.e("Tracking is Loading")
                }
                is Resource.Success -> {
                    loadingWayBill(false)
                    val rajaOngkir = it.data!!.rajaongkir
                    val deliveryStatus = rajaOngkir.result.delivery_status
                    val summary = rajaOngkir.result.summary
                    binding.tvStatus.text = deliveryStatus.status
                    binding.tvReceiver.text = summary.receiver_name
                    binding.tvDate.text = "${deliveryStatus.pod_date} ${deliveryStatus.pod_time}"
                    binding.rvTrackingHistory.apply {
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        adapter = TrackingResultAdapter(it.data.rajaongkir.result.manifest)
                    }
                }
                is Resource.Error -> {
                    loadingWayBill(false)
                    Timber.e("Tracking is error")
                }
            }
        }
    }

    private fun initListener(){
        binding.refreshWaybill.setOnRefreshListener {
            viewModel.fetchWayBill(wayBill!!,courier!!)
        }
    }

    private fun loadingWayBill(loading: Boolean) {
        if (loading) {
            binding.refreshWaybill.isRefreshing = true
            binding.container.visibility = View.GONE
        } else {
            binding.refreshWaybill.isRefreshing = false
            binding.container.visibility = View.VISIBLE
        }
    }
}