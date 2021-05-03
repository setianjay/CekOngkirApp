package com.setianjay.cekongkirapp.ui.waybill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.database.presistence.WayBillEntity
import com.setianjay.cekongkirapp.databinding.ItemWaybillBinding
import java.util.*
import kotlin.collections.ArrayList

class WayBillAdapter(
    private val wayBills: ArrayList<WayBillEntity>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<WayBillAdapter.ViewHolder>() {

    interface OnAdapterListener{
        fun onDelete(data: WayBillEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemWaybillBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = wayBills.size

    override fun onBindViewHolder(holder: WayBillAdapter.ViewHolder, position: Int) {
        val waybill = wayBills[position]
        holder.bind(waybill,listener)
    }

    inner class ViewHolder(private val binding: ItemWaybillBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: WayBillEntity, listener: OnAdapterListener){
            binding.tvLabelWaybill.text = data.wayBill
            binding.tvLabelStatus.text = data.status
            binding.tvLabelCourier.text = data.courier.toUpperCase()

            binding.container.setOnLongClickListener {
                listener.onDelete(data)
                true
            }
        }
    }

    fun setData(data: List<WayBillEntity>){
        wayBills.clear()
        wayBills.addAll(data)
        notifyDataSetChanged()
    }
}