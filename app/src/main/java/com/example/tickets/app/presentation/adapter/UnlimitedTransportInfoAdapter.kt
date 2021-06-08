package com.example.tickets.app.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.app.data.model.Transports
import com.example.tickets.databinding.UnlimitedChooseTransportItemBinding

class UnlimitedTransportInfoAdapter(
    private val transportList: List<Transports>
) : RecyclerView.Adapter<UnlimitedTransportInfoAdapter.ViewHolder>() {

    class ViewHolder(val binding: UnlimitedChooseTransportItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = UnlimitedChooseTransportItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = transportList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transportInfo: Transports = transportList[position]
        with(holder) {
            with(binding) {
                unlimitedTransportName.text = transportInfo.transportName
                unlimitedTransportIcon.setImageResource(addTransportsIcon(position))
                formTransport.setCardBackgroundColor(
                    if (transportInfo.isPressed) Color.rgb(210, 180, 140) else Color.WHITE
                )
                formTransport.setOnClickListener {
                    transportInfo.isPressed = !transportInfo.isPressed
                    formTransport.setCardBackgroundColor(
                        if (transportInfo.isPressed) Color.rgb(210, 180, 140) else Color.WHITE
                    )
                }
            }
        }
    }

    fun getChangeTransport() = transportList.filter { !it.isPressed }

    private fun addTransportsIcon(position: Int): Int {
        return when (transportList[position].id) {
            1 -> R.drawable.icon_bus
            2 -> R.drawable.icon_trolleybus
            3 -> R.drawable.icon_tram
            4 -> R.drawable.icon_express_bus
            5 -> R.drawable.icon_metro
            6 -> R.drawable.icon_train_city_lines
            else -> R.drawable.icon_bus
        }
    }
}