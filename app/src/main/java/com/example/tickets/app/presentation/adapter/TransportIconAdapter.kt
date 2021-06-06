package com.example.tickets.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.databinding.TransportIconItemBinding

class TransportIconAdapter(private val transportIconList: MutableList<Int>) :
    RecyclerView.Adapter<TransportIconAdapter.ViewHolder>() {

    class ViewHolder(val binding: TransportIconItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            TransportIconItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = transportIconList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transportIcon = transportIconList[position]
        holder.binding.transportItemIcon.setImageResource(transportIcon)
    }
}