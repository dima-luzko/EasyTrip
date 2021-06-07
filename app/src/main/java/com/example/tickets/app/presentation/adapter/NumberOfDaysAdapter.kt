package com.example.tickets.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.databinding.NumberDaysItemBinding

class NumberOfDaysAdapter(
    private val numberOfDaysList: List<NumberOfDaysOrTrips>,
    private val chooseNumber: (NumberOfDaysOrTrips) -> Unit
) : RecyclerView.Adapter<NumberOfDaysAdapter.ViewHolder>() {

    class ViewHolder(val binding: NumberDaysItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            NumberDaysItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = numberOfDaysList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val numberOfDays: NumberOfDaysOrTrips = numberOfDaysList[position]
        with(holder){
            with(binding){
                numberOfDaysItem.text = numberOfDays.value.toString()
                numberOfDaysItem.setOnClickListener {
                    chooseNumber(numberOfDays)
                }
            }
        }
    }
}