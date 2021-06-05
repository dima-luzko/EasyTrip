package com.example.tickets.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.app.data.model.UnlimitedTransportInfo
import com.example.tickets.app.presentation.adapter.UnlimitedTransportInfoAdapter
import com.example.tickets.databinding.FragmentUnlimitedChooseBinding
import com.example.tickets.utils.goneBottomNavigation

class UnlimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentUnlimitedChooseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnlimitedChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            numberOfDaysInUnlimitedChooseScreen.text = arguments?.getString("numberOfDays")
            backButtonInUnlimitedChooseScreen.setOnClickListener {
                findNavController().navigate(R.id.action_unlimitedChooseFragment_to_priceFragment)
            }
        }
        equalsNumberOfDays(view)
        addToUnlimitedTransportInfoRecyclerView(view)
    }

    private fun equalsNumberOfDays(view: View) {
        val days = view.findViewById<TextView>(R.id.text_days)
        if (binding.numberOfDaysInUnlimitedChooseScreen.text == "1") {
            days.text = getString(R.string.day)
        }
    }

    private fun addToUnlimitedTransportInfoRecyclerView(view: View) {
        with(binding.unlimitedTransportList) {
            layoutManager = GridLayoutManager(
                context,
                2,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = UnlimitedTransportInfoAdapter(unlimitedTransportInfoList())
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

}