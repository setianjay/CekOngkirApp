package com.setianjay.cekongkirapp.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.databinding.FragmentCityBinding
import com.setianjay.cekongkirapp.databinding.ItemCityBinding
import com.setianjay.cekongkirapp.network.response.CityResponse

class CityAdapter(
    val cityList: MutableList<CityResponse.RajaOngkir.Results>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<CityAdapter.ViewHolder>(){


    interface OnAdapterListener{
        fun onClick(data: CityResponse.RajaOngkir.Results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cityList[position]
        holder.bind(city,listener)
    }

    inner class ViewHolder(val binding: ItemCityBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: CityResponse.RajaOngkir.Results, listener: OnAdapterListener){
            binding.tvCityName.text = data.city_name

            binding.tvCityName.setOnClickListener {
                listener.onClick(data)
            }
        }
    }

    fun setData(data: List<CityResponse.RajaOngkir.Results>){
        cityList.clear()
        cityList.addAll(data)
        notifyDataSetChanged()
    }
}