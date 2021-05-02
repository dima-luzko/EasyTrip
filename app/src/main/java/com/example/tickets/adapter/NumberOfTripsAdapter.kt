package com.example.tickets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.data.NumberOfTrips

class NumberOfTripsAdapter (private val numberOfTripsList: List<NumberOfTrips>): RecyclerView.Adapter<NumberOfTripsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.findViewById(R.id.number_of_trips_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberOfTripsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.number_trips_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = numberOfTripsList.size

    override fun onBindViewHolder(holder: NumberOfTripsAdapter.ViewHolder, position: Int) {
        val numberOfTrips: NumberOfTrips = numberOfTripsList[position]
        holder.number.text = numberOfTrips.numberOfTrips
    }
}