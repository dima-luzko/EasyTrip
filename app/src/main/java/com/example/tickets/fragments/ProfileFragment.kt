package com.example.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.adapter.TransportInfoAdapter
import com.example.tickets.data.TransportInfo
import com.example.tickets.databinding.FragmentProfileBinding
import com.example.tickets.utils.visibleBottomNavigation
import com.simform.custombottomnavigation.SSCustomBottomNavigation


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { visibleBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.personalCardNumber.text = arguments?.getString("cardNumber")
        addToTransportInfoRecyclerView(view)
        requireActivity().findViewById<SSCustomBottomNavigation>(R.id.bottom_navigation)
            .setOnClickMenuListener { menuItem ->
                if (menuItem.id == 2) {
                    findNavController().navigate(R.id.action_profileFragment_to_priceFragment)
                    return@setOnClickMenuListener
                }
            }

        binding.buttonExit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    private fun addToTransportInfoRecyclerView(view: View) {
        with(binding.typeOfTransportsList) {
            layoutManager = GridLayoutManager(
                context,
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


}