package com.setianjay.cekongkirapp.ui.subdistrict

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.databinding.ItemSubdistrictBinding
import com.setianjay.cekongkirapp.network.response.SubDistrictResponse

class SubDistrictAdapter(
    private val districtList: ArrayList<SubDistrictResponse.RajaOngkir.Results>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<SubDistrictAdapter.ViewHolder>(){


    interface OnAdapterListener{
        fun onClick(data: SubDistrictResponse.RajaOngkir.Results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSubdistrictBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int {
        return districtList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val district = districtList[position]
        holder.bind(district,listener)
    }


    inner class ViewHolder(val binding: ItemSubdistrictBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: SubDistrictResponse.RajaOngkir.Results, listener: OnAdapterListener){
            binding.tvSubdistrictName.text = data.subdistrict_name

            binding.container.setOnClickListener {
                listener.onClick(data)
            }
        }
    }

    fun setData(data: List<SubDistrictResponse.RajaOngkir.Results>){
        districtList.clear()
        districtList.addAll(data)
        notifyDataSetChanged()
    }

}