package com.example.tickets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.data.TransportInfo

class TransportInfoAdapter(private val transportList: List<TransportInfo>): RecyclerView.Adapter<TransportInfoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transportName: TextView = view.findViewById(R.id.transport_name)
        val transportImg: ImageView = view.findViewById(R.id.transport_icon)
        val statusImg: ImageView = view.findViewById(R.id.status_image)
        val statusText: TextView = view.findViewById(R.id.status_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportInfoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transport_info_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = transportList.size

    override fun onBindViewHolder(holder: TransportInfoAdapter.ViewHolder, position: Int) {
        val transportInfo: TransportInfo = transportList[position]
        with(holder){
            transportName.text = transportInfo.transportName
            transportImg.setImageResource(transportInfo.transportImg)
            statusImg.setImageResource(transportInfo.statusImg)
            statusText.text = transportInfo.statusText
        }
    }
}