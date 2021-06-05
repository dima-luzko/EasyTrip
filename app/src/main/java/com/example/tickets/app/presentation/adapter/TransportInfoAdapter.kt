package com.example.tickets.app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.app.data.model.Transactions
import com.example.tickets.databinding.TransportInfoItem1Binding

class TransportInfoAdapter(private val transportInfoList: List<Transactions>) :
    RecyclerView.Adapter<TransportInfoAdapter.ViewHolder>() {

//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val finishDate: TextView = view.findViewById(R.id.finish_data_transport_info_item)
//        val countLeftTrips: TextView = view.findViewById(R.id.count_left_trips)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.transport_info_item_1, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount() = transportInfoList.size
//
//    override fun onBindViewHolder(holder: TransportInfoAdapter.ViewHolder, position: Int) {
//        val transportInfo: Transactions = transportInfoList[position]
//        with(holder){
//            finishDate.text = transportInfo.finishData.toString()
//            countLeftTrips.text = transportInfo.numberOfTripLeft?.toString()
//        }
//    }

    class ViewHolder(val binding: TransportInfoItem1Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransportInfoItem1Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = transportInfoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transportInfo: Transactions = transportInfoList[position]
        with(holder) {
            with(binding) {
                finishDataTransportInfoItem.text = transportInfo.finishData.toString()
                countLeftTrips.text = transportInfo.numberOfTripLeft.toString()
            }
        }
    }
}