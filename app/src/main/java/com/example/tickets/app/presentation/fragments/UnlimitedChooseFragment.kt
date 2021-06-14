package com.example.tickets.app.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfDays
import com.example.tickets.app.presentation.viewModel.NumberOfDaysViewModel
import com.example.tickets.app.presentation.viewModel.TransportViewModel
import com.example.tickets.databinding.FragmentUnlimitedChooseBinding
import com.example.tickets.utils.goneBottomNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class UnlimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentUnlimitedChooseBinding
    private val transportViewModel by viewModel<TransportViewModel>()
    private val priceViewModel by viewModel<NumberOfDaysViewModel>()
    private var busButtonIsPressed = false
    private var trolleybusButtonIsPressed = false
    private var tramButtonIsPressed = false
    private var busExpressButtonIsPressed = false
    private var metroButtonIsPressed = false
    private var trainCityLinesButtonIsPressed = false
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
    ): View {
        binding = FragmentUnlimitedChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { goneBottomNavigation(it) }

        numberOfDaysId = arguments?.getInt("numberOfDaysId")!!.toInt()

        setTransportName()
        handleClick()
        binding.numberOfDaysInUnlimitedChooseScreen.text = arguments?.getString("numberOfDays")
        equalsNumberOfDays()

//            priceViewModel.price.observe(viewLifecycleOwner, Observer {
//                binding.unlimitedCountOfRubles.text = it.toString()
//            })
    }

    private fun handleClick() {


        with(binding) {
            backButtonInUnlimitedChooseScreen.setOnClickListener {
                findNavController().popBackStack()
            }
            busButton.setOnClickListener {
                busButtonIsPressed = !busButtonIsPressed
                onActiveButton(busButtonIsPressed, busButton)

                if (busExpressButtonIsPressed) {
                    busButtonIsPressed = true
                    onActiveButton(busButtonIsPressed, busButton)
                    transportListID.remove(busID)
                }

                addTransportToArrayList(busButtonIsPressed, busID)
            }
            trolleybusButton.setOnClickListener {

                trolleybusButtonIsPressed = !trolleybusButtonIsPressed
                onActiveButton(trolleybusButtonIsPressed, trolleybusButton)
                addTransportToArrayList(trolleybusButtonIsPressed, trolleybusID)
            }
            tramButton.setOnClickListener {
                tramButtonIsPressed = !tramButtonIsPressed
                onActiveButton(tramButtonIsPressed, tramButton)

                addTransportToArrayList(tramButtonIsPressed, tramID)
            }
            busExpressButton.setOnClickListener {
                busExpressButtonIsPressed = !busExpressButtonIsPressed
                onActiveButton(busExpressButtonIsPressed, busExpressButton)

                if (busExpressButtonIsPressed) {
                    busButtonIsPressed = true
                    onActiveButton(busButtonIsPressed, busButton)
                    addTransportToArrayList(busButtonIsPressed, busID)
                }
                addTransportToArrayList(busExpressButtonIsPressed, busExpressID)

            }
            metroButton.setOnClickListener {
                metroButtonIsPressed = !metroButtonIsPressed
                onActiveButton(metroButtonIsPressed, metroButton)
                addTransportToArrayList(metroButtonIsPressed, metroID)
            }
            trainCityLinesButton.setOnClickListener {
                trainCityLinesButtonIsPressed = !trainCityLinesButtonIsPressed
                onActiveButton(trainCityLinesButtonIsPressed, trainCityLinesButton)
                addTransportToArrayList(
                    trainCityLinesButtonIsPressed,
                    trainCityLinesID
                )
            }

            buttonGetPrice.setOnClickListener {
//                priceViewModel.getPrice(
//                    BodyForGetPriceByNumberOfDays(
//                    numberOfDaysId = numberOfDaysId ,
//                    transports = transportListID,
//                    count = transportListID.size
//                ))
                Toast.makeText(context, transportListID.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun checkNumberOfDays(){
//        with(binding) {
//            if (numberOfDaysInUnlimitedChooseScreen.text == "1" || numberOfDaysInUnlimitedChooseScreen.text == "2") {
//
//            }
//        }
//    }

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
                val trainCityLines = transportName.train_city_lines
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

//    private fun getFinalPrice() {
//        priceViewModel.getPrice(body)
//
//    }


    private fun equalsNumberOfDays() {
        with(binding) {
            if (numberOfDaysInUnlimitedChooseScreen.text == "1") {
                textDays.text = getString(R.string.day)
            }
        }
    }

//    private val body = BodyForGetPriceByNumberOfDays(
//        numberOfDaysId = numberOfDaysId ,
//        transports = transportListID,
//        count = transportListID.size
//    )
}




