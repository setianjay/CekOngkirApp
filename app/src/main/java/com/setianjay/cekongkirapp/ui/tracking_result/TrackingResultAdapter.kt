package com.setianjay.cekongkirapp.ui.tracking_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.databinding.ItemTrackingBinding
import com.setianjay.cekongkirapp.network.response.WayBillResponse

class TrackingResultAdapter(
    private val histories: List<WayBillResponse.RajaOngkir.Result.Manifest>
): RecyclerView.Adapter<TrackingResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(ItemTrackingBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = histories.size

    override fun onBindViewHolder(holder: TrackingResultAdapter.ViewHolder, position: Int) {
        val history = histories[position]
        holder.bind(history)
    }

    inner class ViewHolder(private val binding: ItemTrackingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: WayBillResponse.RajaOngkir.Result.Manifest){
            binding.tvDate.text = data.manifest_date
            binding.tvDescription.text = data.manifest_description
        }
    }
}