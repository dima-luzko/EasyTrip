package com.example.tickets

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.adapter.TransportInfoAdapter
import com.example.tickets.data.TransportInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_screen)
        hideSystemUI()
        addToTransportInfoRecyclerView()
    }

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