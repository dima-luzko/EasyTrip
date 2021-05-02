package com.example.tickets

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.adapter.NumberOfDaysAdapter
import com.example.tickets.adapter.NumberOfTripsAdapter
import com.example.tickets.adapter.TransportInfoAdapter
import com.example.tickets.data.NumberOfDays
import com.example.tickets.data.NumberOfTrips
import com.example.tickets.data.TransportInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.price_screen)
        hideSystemUI()
        //addToTransportInfoRecyclerView()
        addToNumberOfDaysRecyclerView()
        addToNumberOfTripsRecyclerView()
    }

    private fun addToNumberOfDaysRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.number_of_days_list)
        with(recyclerView) {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                3,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = NumberOfDaysAdapter(numberOfDaysList())
            hasFixedSize()
        }
    }

    private fun addToNumberOfTripsRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.number_of_trips_list)
        with(recyclerView) {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                3,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = NumberOfTripsAdapter(numberOfTripsList())
            hasFixedSize()
        }
    }

    private fun numberOfTripsList() = listOf(
        NumberOfTrips(
           numberOfTrips = "1"
        ),
        NumberOfTrips(
            numberOfTrips = "10"
        ),
        NumberOfTrips(
            numberOfTrips = "20"
        ),
        NumberOfTrips(
            numberOfTrips = "25"
        ),
        NumberOfTrips(
            numberOfTrips = "30"
        ),
        NumberOfTrips(
            numberOfTrips = "40"
        ),
        NumberOfTrips(
            numberOfTrips = "50"
        ),
        NumberOfTrips(
            numberOfTrips = "60"
        ),
        NumberOfTrips(
            numberOfTrips = "100"
        )
    )

    private fun numberOfDaysList() = listOf(
        NumberOfDays(
            numberOfDays = "1"
        ),
        NumberOfDays(
            numberOfDays = "2"
        ),
        NumberOfDays(
            numberOfDays = "3"
        ),
        NumberOfDays(
            numberOfDays = "5"
        ),
        NumberOfDays(
            numberOfDays = "10"
        ),
        NumberOfDays(
            numberOfDays = "15"
        ),
        NumberOfDays(
            numberOfDays = "20"
        ),
        NumberOfDays(
            numberOfDays = "30"
        ),
        NumberOfDays(
            numberOfDays = "90"
        )
    )

    private fun addToTransportInfoRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.type_of_transports_list)
        with(recyclerView) {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                2,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = TransportInfoAdapter(transportInfoList())
            hasFixedSize()
        }
    }

    private fun transportInfoList() = listOf(
        TransportInfo(
            transportName = getString(R.string.bus),
            transportImg = R.drawable.icon_bus,
            statusImg = R.drawable.icon_circle_orange,
            statusText = getString(R.string.ends)
        ),
        TransportInfo(
            transportName = getString(R.string.trolleybus),
            transportImg = R.drawable.icon_trolleybus,
            statusImg = R.drawable.icon_circle_green,
            statusText = getString(R.string.active)
        ),
        TransportInfo(
            transportName = getString(R.string.tram),
            transportImg = R.drawable.icon_tram,
            statusImg = R.drawable.icon_circle_red,
            statusText = getString(R.string.not_active)
        ),
        TransportInfo(
            transportName = getString(R.string.bus_express),
            transportImg = R.drawable.icon_express_bus,
            statusImg = R.drawable.icon_circle_green,
            statusText = getString(R.string.active)
        ),
        TransportInfo(
            transportName = getString(R.string.metro),
            transportImg = R.drawable.icon_metro,
            statusImg = R.drawable.icon_circle_orange,
            statusText = getString(R.string.ends)
        ),
        TransportInfo(
            transportName = getString(R.string.train_city_lines),
            transportImg = R.drawable.icon_train_city_lines,
            statusImg = R.drawable.icon_circle_red,
            statusText = getString(R.string.not_active)
        )
    )

    @Suppress("DEPRECATION")
    private fun hideSystemUI() {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) {
            window.decorView.apply {
                systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }
}