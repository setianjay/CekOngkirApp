package com.setianjay.cekongkirapp.ui.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.databinding.ItemCourierBinding
import com.setianjay.cekongkirapp.network.response.CostResponse

class CostAdapter(
    private val courierList: ArrayList<CostResponse.RajaOngkir.Results>
): RecyclerView.Adapter<CostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCourierBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int = courierList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courier = courierList[position]
        holder.bind(courier)
    }

    inner class ViewHolder(private val binding: ItemCourierBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: CostResponse.RajaOngkir.Results){
            binding.tvCode.text = data.code
            binding.tvName.text = data.name
            binding.listService.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                adapter = ServiceAdapter(data.costs)
                setHasFixedSize(true)
            }
        }
    }

    fun setData(data: List<CostResponse.RajaOngkir.Results>){
        courierList.clear()
        courierList.addAll(data)
        notifyDataSetChanged()
    }
}