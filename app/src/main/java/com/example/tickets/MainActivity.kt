package com.example.tickets

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.adapter.*
import com.example.tickets.data.*
import com.simform.custombottomnavigation.SSCustomBottomNavigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
        val bottomNavigation: SSCustomBottomNavigation = findViewById(R.id.bottom_navigation)
        with(bottomNavigation) {
            add(SSCustomBottomNavigation.Model(1, R.drawable.profile_button,getString(R.string.profile)))
            add(SSCustomBottomNavigation.Model(2, R.drawable.price_button,getString(R.string.price)))
            show(1)
        }

       //addToTransportInfoRecyclerView()
       //addToNumberOfDaysRecyclerView()
        //addToUnlimitedTransportInfoRecyclerView()
//        val button: AppCompatButton = findViewById(R.id.unlimited_trips_button)
//        button.setOnClickListener {
//            showSharePopupDialog()
//        }
    }

    private fun showSharePopupDialog() {
        val dialog = Dialog(this)
        with(dialog) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.unlimited_trips_popup_window)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        val recyclerView = dialog.findViewById<RecyclerView>(R.id.number_of_days_list)
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
        dialog.show()
    }

    private fun addToUnlimitedTransportInfoRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.unlimited_transport_list)
        with(recyclerView) {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                2,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = UnlimitedTransportInfoAdapter(unlimitedTransportInfoList())
            hasFixedSize()
        }
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

    private fun unlimitedTransportInfoList() = listOf(
        UnlimitedTransportInfo(
            transportName = getString(R.string.bus),
            icon = R.drawable.icon_bus,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.trolleybus),
            icon = R.drawable.icon_trolleybus,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.tram),
            icon = R.drawable.icon_tram,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.bus_express),
            icon = R.drawable.icon_express_bus,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.metro),
            icon = R.drawable.icon_metro,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.train_city_lines),
            icon = R.drawable.icon_train_city_lines,
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