package com.example.tickets.app.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.app.data.model.Transactions
import com.example.tickets.databinding.TransportInfoItemBinding
import java.text.SimpleDateFormat
import java.util.*


class TransportInfoAdapter(private val transportInfoList: List<Transactions>) :
    RecyclerView.Adapter<TransportInfoAdapter.ViewHolder>() {

    class ViewHolder(val binding: TransportInfoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransportInfoItemBinding.inflate(
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
                finishDataTransportInfoItem.text = transportInfo.finishData.replace("-", ".")
                countLeftTrips.text = changeNullToText(position)
                colorFormTransportInfoItem.setCardBackgroundColor(
                    getNumberOfDaysBetweenTwoDates(
                        position
                    )
                )
                if (colorFormTransportInfoItem.cardBackgroundColor.defaultColor == Color.rgb(
                        255,
                        152,
                        0
                    )
                ) {
                    colorFormTransportInfoItem.startAnimation(blink())
                }
                transportsIconRecyclerView(position, transportInfoIconList)
            }
        }
    }

    private fun transportsIconRecyclerView(position: Int, recyclerView: RecyclerView) {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                context,
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = TransportIconAdapter(addTransportsIcon(position))
            hasFixedSize()
        }
    }

    private fun blink(): Animation {
        val animation: Animation = AlphaAnimation(0.0f, 1.0f)
        with(animation) {
            duration = 500
            startOffset = 50
            repeatCount = Animation.INFINITE
        }
        return animation
    }

    private fun getNumberOfDaysBetweenTwoDates(position: Int): Int {
        val transportInfo: Transactions = transportInfoList[position]

        val date = android.text.format.DateFormat.format(
            "yyyy-MM-dd",
            Calendar.getInstance()
        )
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = dateFormat.parse(date.toString())
        val finishDate = dateFormat.parse(transportInfo.finishData)
        val milliseconds = finishDate!!.time - currentDate!!.time
        val numberOfDays = (milliseconds / (24 * 60 * 60 * 1000)).toInt()
        var color = Color.rgb(255, 255, 255)

        if (transportInfo.numberOfTripLeft in 1..5 || numberOfDays in 1..2) {
            color = Color.rgb(255, 152, 0)
        } else if (transportInfo.numberOfTripLeft == 0 || numberOfDays < 0) {
            color = Color.rgb(244, 67, 54)
        } else if (transportInfo.numberOfTripLeft in 6..100 || numberOfDays > 2) {
            color = Color.rgb(0, 100, 0)
        }
        return color
    }

    private fun changeNullToText(position: Int): String {
        var numberOfTripLeft = transportInfoList[position].numberOfTripLeft?.toString()
        if (numberOfTripLeft == null) {
            numberOfTripLeft = "безлимит"
        }
        return numberOfTripLeft
    }

    private fun addTransportsIcon(position: Int): MutableList<Int> {
        val list = mutableListOf<Int>()
        for (icon in transportInfoList[position].tarif.transports) {
            when (icon.id) {
                1 -> list.add(R.drawable.icon_bus)
                2 -> list.add(R.drawable.icon_trolleybus)
                3 -> list.add(R.drawable.icon_tram)
                4 -> list.add(R.drawable.icon_express_bus)
                5 -> list.add(R.drawable.icon_metro)
                6 -> list.add(R.drawable.icon_train_city_lines)
            }
        }
        return list
    }

}