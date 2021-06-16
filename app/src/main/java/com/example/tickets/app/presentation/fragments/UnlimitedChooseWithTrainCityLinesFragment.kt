package com.example.tickets.app.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfDays
import com.example.tickets.app.presentation.viewModel.NumberOfDaysViewModel
import com.example.tickets.app.presentation.viewModel.TransportViewModel
import com.example.tickets.databinding.FragmentUnlimitedChooseWithTrainCityLinesBinding
import com.example.tickets.utils.goneBottomNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal

class UnlimitedChooseWithTrainCityLinesFragment : Fragment() {

    private lateinit var binding: FragmentUnlimitedChooseWithTrainCityLinesBinding
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
        binding =
            FragmentUnlimitedChooseWithTrainCityLinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
        numberOfDaysId = arguments?.getInt("numberOfDaysId")!!.toInt()
        binding.numberOfDaysInUnlimitedChooseScreen.text = arguments?.getString("numberOfDays")
        setTransportName()
        handleClick()
        getFinalPrice()
        setFinalPrice()
    }

    private fun setFinalPrice() {
        priceViewModel.price.observe(viewLifecycleOwner, Observer { countPrice ->
            val price = countPrice.toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()
                .split(".")
            val rubles = price.first()
            val penny = price.last()
            with(binding) {
                unlimitedCountOfRubles.text = rubles
                unlimitedCountOfPenny.text = penny
            }
        })
    }

    private fun getFinalPrice() {
        if (transportListID.isEmpty()) {
            binding.unlimitedCountOfRubles.text = getString(R.string.zero)
            binding.unlimitedCountOfPenny.text = getString(R.string.double_zero)
        } else {
            priceViewModel.getPrice(
                BodyForGetPriceByNumberOfDays(
                    numberOfDaysId = numberOfDaysId,
                    transports = transportListID,
                    count = transportListID.size
                )
            )
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

    private fun handleClick() {
        with(binding) {
            backButtonInUnlimitedChooseScreen.setOnClickListener {
                findNavController().popBackStack()
            }
            busButton.setOnClickListener {
                busButtonIsPressed = !busButtonIsPressed
                onActiveButton(busButtonIsPressed, busButton)
                addTransportToArrayList(busButtonIsPressed, busID)
                onActiveTrainCityLinesButton()
                onActiveBusExpressButton(
                    !busButtonIsPressed,
                    trolleybusButtonIsPressed,
                    tramButtonIsPressed
                )
                getFinalPrice()
            }
            trolleybusButton.setOnClickListener {
                trolleybusButtonIsPressed = !trolleybusButtonIsPressed
                onActiveButton(trolleybusButtonIsPressed, trolleybusButton)
                addTransportToArrayList(trolleybusButtonIsPressed, trolleybusID)
                onActiveTrainCityLinesButton()
                onActiveBusExpressButton(
                    busButtonIsPressed,
                    !trolleybusButtonIsPressed,
                    tramButtonIsPressed
                )
                getFinalPrice()
            }
            tramButton.setOnClickListener {
                tramButtonIsPressed = !tramButtonIsPressed
                onActiveButton(tramButtonIsPressed, tramButton)
                addTransportToArrayList(tramButtonIsPressed, tramID)
                onActiveTrainCityLinesButton()
                onActiveBusExpressButton(
                    busButtonIsPressed,
                    trolleybusButtonIsPressed,
                    !tramButtonIsPressed
                )
                getFinalPrice()
            }
            busExpressButton.setOnClickListener(null)
            metroButton.setOnClickListener {
                metroButtonIsPressed = !metroButtonIsPressed
                onActiveButton(metroButtonIsPressed, metroButton)
                addTransportToArrayList(metroButtonIsPressed, metroID)
                onActiveTrainCityLinesButton()
                getFinalPrice()
            }
            trainCityLinesButton.setOnClickListener {
                trainCityLinesButtonIsPressed = !trainCityLinesButtonIsPressed
                onActiveButton(trainCityLinesButtonIsPressed, trainCityLinesButton)
                addTransportToArrayList(trainCityLinesButtonIsPressed, trainCityLinesID)
                onActiveTrainCityLinesButton()
                getFinalPrice()
            }
        }
    }

    private fun onActiveBusExpressButton(
        busFlag: Boolean,
        trolleybusFlag: Boolean,
        tramFlag: Boolean
    ) {
        if (busFlag && trolleybusFlag && tramFlag && busExpressButtonIsPressed) {
            with(binding) {
                busExpressButton.visibility = ConstraintLayout.VISIBLE
                busExpressButtonName.visibility = ConstraintLayout.VISIBLE
                busExpressButtonIcon.visibility = ConstraintLayout.VISIBLE
                busExpressButtonIsPressed = !busExpressButtonIsPressed
                onActiveButton(busExpressButtonIsPressed, busExpressButton)
                addTransportToArrayList(busExpressButtonIsPressed, busExpressID)
                busExpressButton.visibility = ConstraintLayout.GONE
                busExpressButtonName.visibility = ConstraintLayout.GONE
                busExpressButtonIcon.visibility = ConstraintLayout.GONE
            }
        }
    }

    private fun onActiveTrainCityLinesButton() {
        if (busButtonIsPressed || trolleybusButtonIsPressed || tramButtonIsPressed || metroButtonIsPressed) {
            trainCityLinesButtonIsPressed = true
            onActiveButton(trainCityLinesButtonIsPressed, binding.trainCityLinesButton)
            addTransportToArrayList(trainCityLinesButtonIsPressed, trainCityLinesID)
        }
        if (busButtonIsPressed && trolleybusButtonIsPressed && tramButtonIsPressed) {
            with(binding){
                busExpressButton.visibility = ConstraintLayout.VISIBLE
                busExpressButtonName.visibility = ConstraintLayout.VISIBLE
                busExpressButtonIcon.visibility = ConstraintLayout.VISIBLE
                trainCityLinesButtonIsPressed = true
                busExpressButtonIsPressed = true
                onActiveButton(trainCityLinesButtonIsPressed, trainCityLinesButton)
                onActiveButton(busExpressButtonIsPressed, busExpressButton)
                addTransportToArrayList(trainCityLinesButtonIsPressed, trainCityLinesID)
                addTransportToArrayList(busExpressButtonIsPressed, busExpressID)
            }
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