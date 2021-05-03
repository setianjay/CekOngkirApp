package com.setianjay.cekongkirapp.ui.waybill

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.cekongkirapp.database.presistence.WayBillEntity
import com.setianjay.cekongkirapp.databinding.FragmentWayBillBinding
import com.setianjay.cekongkirapp.ui.tracking.TrackingActivity
import com.setianjay.cekongkirapp.ui.tracking.TrackingViewModel
import timber.log.Timber


class WayBillFragment : Fragment() {
    private lateinit var binding: FragmentWayBillBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }
    private lateinit var wayBillAdapter: WayBillAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWayBillBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        setupObserver()
        initListener()
    }

    private fun setupRecycleView(){
        wayBillAdapter = WayBillAdapter(arrayListOf(), object : WayBillAdapter.OnAdapterListener {
            override fun onDelete(data: WayBillEntity) {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.apply {
                    setTitle("Hapus Resi")
                    setMessage("Hapus No. Resi ${data.wayBill}?")
                    setPositiveButton("Hapus"){ _,_ ->
                        viewModel.deleteWayBill(data)
                    }
                    setNegativeButton("Tidak"){_,_ ->

                    }
                    show()
                }
            }

        })

        binding.rvListTracking.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = wayBillAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupObserver(){
        viewModel.waybill.observe(viewLifecycleOwner){
//            Timber.e("waybill: $it")
            wayBillAdapter.setData(it)
        }
    }

    private fun initListener(){
        binding.etSearch.setOnClickListener {
            Intent(requireContext(),TrackingActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}