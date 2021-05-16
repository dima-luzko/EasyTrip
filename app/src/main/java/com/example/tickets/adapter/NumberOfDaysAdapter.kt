package com.example.tickets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.data.NumberOfDays

class NumberOfDaysAdapter(
    private val numberOfDaysList: List<NumberOfDays>,
    private val chooseNumber: (NumberOfDays) -> Unit
) :
    RecyclerView.Adapter<NumberOfDaysAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.findViewById(R.id.number_of_days_item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.number_days_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = numberOfDaysList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val numberOfDays: NumberOfDays = numberOfDaysList[position]
        with(holder){
            number.text = numberOfDays.numberOfDays
            number.setOnClickListener {
                chooseNumber(numberOfDays)
            }
        }
    }
}