package com.setianjay.cekongkirapp.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.databinding.FragmentCityBinding
import com.setianjay.cekongkirapp.databinding.ItemCityBinding
import com.setianjay.cekongkirapp.network.response.CityResponse
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class CityAdapter(
    private val cityList: ArrayList<CityResponse.RajaOngkir.Results>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<CityAdapter.ViewHolder>(), Filterable{

    private var cityListFilter = ArrayList<CityResponse.RajaOngkir.Results>()

    init {
        cityListFilter = cityList
    }

    interface OnAdapterListener{
        fun onClick(data: CityResponse.RajaOngkir.Results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int = cityListFilter.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cityListFilter[position]
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch : $charSearch")

                if (charSearch.isEmpty()){
                    cityListFilter.addAll(cityList)
                }else{
                    val citiesFiltered = ArrayList<CityResponse.RajaOngkir.Results>()
                    for (city in cityList){
                        if (city.city_name.toLowerCase(Locale.getDefault()).contains(charSearch.toLowerCase(Locale.getDefault()))){
                            citiesFiltered.add(city)
                        }
                    }
                    cityListFilter = citiesFiltered
                }

                val citiesFilteredResult = FilterResults()
                citiesFilteredResult.values = cityListFilter
                return citiesFilteredResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                cityListFilter = results?.values as ArrayList<CityResponse.RajaOngkir.Results>
                notifyDataSetChanged()
            }

        }
    }
}