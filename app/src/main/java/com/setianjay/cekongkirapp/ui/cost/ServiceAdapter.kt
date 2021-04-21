package com.setianjay.cekongkirapp.ui.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.databinding.ItemServiceBinding
import com.setianjay.cekongkirapp.network.response.CostResponse

class ServiceAdapter(
    private val serviceList: List<CostResponse.RajaOngkir.Results.Costs>
): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemServiceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int = serviceList.size

    override fun onBindViewHolder(holder: ServiceAdapter.ViewHolder, position: Int) {
        val service = serviceList[position]
        holder.bind(service)
    }

    inner class ViewHolder(private val binding: ItemServiceBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: CostResponse.RajaOngkir.Results.Costs){
            binding.tvService.text = data.service
            binding.tvDescription.text = data.description
            binding.tvEtd.text = data.cost[0].etd
            binding.tvPrice.text = data.cost[0].value.toString()
        }
    }


}