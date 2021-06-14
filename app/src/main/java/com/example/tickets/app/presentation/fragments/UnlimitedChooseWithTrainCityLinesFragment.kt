package com.example.tickets.app.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickets.app.presentation.viewModel.NumberOfDaysViewModel
import com.example.tickets.app.presentation.viewModel.TransportViewModel
import com.example.tickets.databinding.FragmentUnlimitedChooseWithTrainCityLinesBinding
import com.example.tickets.utils.goneBottomNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer

class UnlimitedChooseWithTrainCityLinesFragment : Fragment() {

    private lateinit var binding: FragmentUnlimitedChooseWithTrainCityLinesBinding
    private val transportViewModel by viewModel<TransportViewModel>()
    private val priceViewModel by viewModel<NumberOfDaysViewModel>()
    private var busButtonIsPressed = false
    private var trolleybusButtonIsPressed = false
    private var tramButtonIsPressed = false
    private var busExpressButtonIsPressed = false
    private var metroButtonIsPressed = false
    private val busID = 1
    private val trolleybusID = 2
    private val tramID = 3
    private val busExpressID = 4
    private val metroID = 5
    private val trainCityLinesID = 6
    private var transportListID = arrayListOf<Int>()
    private var numberOfDaysId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentUnlimitedChooseWithTrainCityLinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
        numberOfDaysId = arguments?.getInt("numberOfDaysId")!!.toInt()
        binding.numberOfDaysInUnlimitedChooseScreen.text = arguments?.getString("numberOfDays")
        setTransportName()
    }

    @SuppressLint("SetTextI18n")
    private fun setTransportName() {
        with(transportViewModel) {
            getTransportName()
            transportName.observe(viewLifecycleOwner, Observer
            { transportName ->
                val bus = transportName.bus
                val trolleybus = transportName.trolleybus
                val tram = transportName.tram
                val busExpress = transportName.busExpress
                val metro = transportName.metro
                val trainCityLines = transportName.trainCityLines
                with(binding) {
                    busButtonName.text = bus
                    trolleybusButtonName.text = trolleybus
                    tramButtonName.text = tram
                    busExpressButtonName.text = busExpress
                    metroButtonName.text = metro
                    trainCityLinesButtonName.text = trainCityLines
                }
            })
        }
    }

    private fun addTransportToArrayList(flag: Boolean, transportId: Int) {
        var checkElement = false
        if (flag) {
            transportListID.forEach {
                if (it == transportId) {
                    checkElement = true
                }
            }
            if (!checkElement) {
                transportListID.add(transportId)
                transportListID.sort()
            }
        }
        if (!flag) {
            transportListID.remove(transportId)
        }
    }

    private fun onActiveButton(flag: Boolean, button: View) {
        if (flag) {
            button.backgroundTintList = ColorStateList.valueOf(Color.rgb(210, 180, 140))
        } else {
            button.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 255, 255))
        }
    }

}