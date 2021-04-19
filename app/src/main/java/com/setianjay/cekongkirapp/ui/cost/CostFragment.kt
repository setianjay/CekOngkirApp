package com.setianjay.cekongkirapp.ui.cost

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.database.preferences.PreferencesModel
import com.setianjay.cekongkirapp.databinding.FragmentCostBinding
import com.setianjay.cekongkirapp.ui.city.CityActivity


class CostFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CostViewModel::class.java) }
    private lateinit var binding: FragmentCostBinding
    private var originId: String? = ""
    private var destinationId: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setupObserve()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPreferences()
    }

    private fun initListener() {
        binding.etCityOrigin.setOnClickListener {
            Intent(context, CityActivity::class.java).also {
                it.putExtra("type", "origin")
                startActivity(it)
            }
        }

        binding.etCityDestination.setOnClickListener {
            Intent(context, CityActivity::class.java).also {
                it.putExtra("type", "destination")
                startActivity(it)
            }
        }
    }

    private fun setupObserve() {
        viewModel.preferences.observe(viewLifecycleOwner
        ) { preferencesList: List<PreferencesModel> ->
            preferencesList.forEach {
                when (it.type) {
                    "origin" -> {
                        originId = it.id
                        binding.etCityOrigin.setText(it.name)
                    }
                    "destination" -> {
                        destinationId = it.id
                        binding.etCityDestination.setText(it.name)
                    }
                }
            }
        }
    }
}