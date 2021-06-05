package com.example.tickets.app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.app.data.model.UnlimitedTransportInfo

class UnlimitedTransportInfoAdapter(private val transportList: List<UnlimitedTransportInfo>) :
    RecyclerView.Adapter<UnlimitedTransportInfoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transportName: TextView = view.findViewById(R.id.unlimited_transport_name)
        val icon: ImageView = view.findViewById(R.id.unlimited_transport_icon)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnlimitedTransportInfoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.unlimited_choose_transport_item, parent, false)
        return UnlimitedTransportInfoAdapter.ViewHolder(view)
    }

    override fun getItemCount() = transportList.size

    override fun onBindViewHolder(holder: UnlimitedTransportInfoAdapter.ViewHolder, position: Int) {
        val transportInfo : UnlimitedTransportInfo = transportList[position]
        with(holder){
            transportName.text = transportInfo.transportName
            icon.setImageResource(transportInfo.icon)
        }
    }


}