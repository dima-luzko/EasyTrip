package com.example.tickets.app.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfTrips
import com.example.tickets.app.presentation.viewModel.NumberOfTripsViewModel
import com.example.tickets.app.presentation.viewModel.TransportViewModel
import com.example.tickets.databinding.FragmentLimitedChooseBinding
import com.example.tickets.utils.goneBottomNavigation
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal


class LimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentLimitedChooseBinding
    private var currentIndexFirstItem = 0
    private var currentIndexSecondItem = 0
    private var currentIndexThirdItem = 0
    private val numberOfTripsViewModel by viewModel<NumberOfTripsViewModel>()
    private val transportViewModel by viewModel<TransportViewModel>()
    private val firstTransportList = arrayListOf(1, 2, 3)
    private val secondTransportList = arrayListOf(5)
    private val thirdTransportList = arrayListOf(4)

    private val numberOfTripsList = numberOfTripsViewModel.numberOfTripsList.map { it.value }
    private val numberOfTripListForMetro =
        numberOfTripsViewModel.numberOfTripsListForMetro.map { it.value }
            .filterIndexed { index, _ ->
                index != 1 && index != 2 && index != 3 && index != 6 && index != 11
            }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLimitedChooseBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
        setTransportName()
        setNumberOfTripsList()
        setFinalPrice()
        handleClick()
    }

    private fun setNumberOfTripsList() {
        with(numberOfTripsViewModel) {
            getNumberOfTrips()
            getNumberOfTripsForMetro()
            with(binding) {
                numberOfTrips.observe(viewLifecycleOwner, Observer { numberOfTrips ->
                    textListInFirstLimitedItem.text = numberOfTrips.first().value.toString()
                    textListInThirdLimitedItem.text = numberOfTrips.first().value.toString()
                })
                numberOfTripsForMetro.observe(viewLifecycleOwner, Observer {
                    textListInSecondLimitedItem.text = it.first().value.toString()
                })
            }
        }
    }

    private fun setFinalPrice() {
        numberOfTripsViewModel.price.observe(viewLifecycleOwner, Observer { countPrice ->
            val price = countPrice.toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()
                .split(".")
            val rubles = price.first()
            val penny = price.last()

            with(binding) {
                limitedCountOfRubles.text = rubles
                limitedCountOfPenny.text = penny
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setTransportName() {
        with(transportViewModel) {
            getTransport()
            transportName.observe(viewLifecycleOwner, Observer
            { transportName ->
                val bus = transportName.bus
                val trolleybus = transportName.trolleybus
                val tram = transportName.tram
                val busExpress = transportName.busExpress
                val metro = transportName.metro
                with(binding) {
                    nameInFirstLimitedItem.text =
                        "$bus - $trolleybus - $tram"
                    nameInSecondLimitedItem.text = metro
                    nameInThirdLimitedItem.text = "$busExpress.\""
                }
            })
        }
    }

    private fun handleClick() {
        with(binding) {
            backButtonInLimitedChooseScreen.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonUpInFirstLimitedItem.setOnClickListener {
                currentIndexFirstItem = incrementIndex(
                    buttonUpInFirstLimitedItem,
                    buttonDownInFirstLimitedItem,
                    textListInFirstLimitedItem,
                    currentIndexFirstItem,
                    numberOfTripsList
                )
            }
            buttonDownInFirstLimitedItem.setOnClickListener {
                currentIndexFirstItem = decrementIndex(
                    buttonUpInFirstLimitedItem,
                    buttonDownInFirstLimitedItem,
                    textListInFirstLimitedItem,
                    currentIndexFirstItem,
                    numberOfTripsList
                )
            }

            buttonUpInSecondLimitedItem.setOnClickListener {
                currentIndexSecondItem = incrementIndex(
                    buttonUpInSecondLimitedItem,
                    buttonDownInSecondLimitedItem,
                    textListInSecondLimitedItem,
                    currentIndexSecondItem,
                    numberOfTripListForMetro
                )
            }
            buttonDownInSecondLimitedItem.setOnClickListener {
                currentIndexSecondItem = decrementIndex(
                    buttonUpInSecondLimitedItem,
                    buttonDownInSecondLimitedItem,
                    textListInSecondLimitedItem,
                    currentIndexSecondItem,
                    numberOfTripListForMetro
                )
            }

            buttonUpInThirdLimitedItem.setOnClickListener {
                currentIndexThirdItem = incrementIndex(
                    buttonUpInThirdLimitedItem,
                    buttonDownInThirdLimitedItem,
                    textListInThirdLimitedItem,
                    currentIndexThirdItem,
                    numberOfTripsList
                )
            }
            buttonDownInThirdLimitedItem.setOnClickListener {
                currentIndexThirdItem = decrementIndex(
                    buttonUpInThirdLimitedItem,
                    buttonDownInThirdLimitedItem,
                    textListInThirdLimitedItem,
                    currentIndexThirdItem,
                    numberOfTripsList
                )
            }

            buttonGetPriceLimitedScreen.setOnClickListener {
                getFinalPrice()
            }
        }
    }

    private fun getFinalPrice() {
        numberOfTripsViewModel.getPrice(
            body(
                getIdByNumberOfTripList(currentIndexFirstItem, numberOfTripsList),
                firstTransportList,
                firstTransportList.size
            ),
            body(
                getIdByNumberOfTripList(currentIndexSecondItem, numberOfTripListForMetro),
                secondTransportList,
                secondTransportList.size
            ),
            body(
                getIdByNumberOfTripList(currentIndexThirdItem, numberOfTripsList),
                thirdTransportList,
                thirdTransportList.size
            )
        )
    }

    private fun getIdByNumberOfTripList(index: Int, numberOfTripsListValue: List<Int>): Int {
        numberOfTripsViewModel.numberOfTripsList.forEach {
            if (it.value == numberOfTripsListValue[index]) {
                return it.id
            }
        }
        return 0
    }

    private fun body(
        id: Int?,
        transports: ArrayList<Int>,
        count: Int
    ): BodyForGetPriceByNumberOfTrips {
        return BodyForGetPriceByNumberOfTrips(
            numberOfTripsId = id,
            transports = transports,
            count = count
        )
    }

    private fun incrementIndex(
        buttonUp: MaterialButton,
        buttonDown: MaterialButton,
        textList: TextView,
        currentIndex: Int,
        list: List<Int>
    ): Int {
        var index = currentIndex
        ++index
        if (index < list.size) {
            textList.text = list[index].toString()
            if (index == 1) {
                buttonDown.visibility = ConstraintLayout.VISIBLE
            } else if (index == list.lastIndex) {
                buttonUp.visibility =
                    ConstraintLayout.INVISIBLE
            }
            return index
        }
        return -1
    }

    private fun decrementIndex(
        buttonUp: MaterialButton,
        buttonDown: MaterialButton,
        textList: TextView,
        currentIndex: Int,
        list: List<Int>
    ): Int {
        var index = currentIndex
        --index
        if (index <= list.size) {
            textList.text = list[index].toString()
            if (index == 0) {
                buttonDown.visibility =
                    ConstraintLayout.INVISIBLE
            } else if (index == list.lastIndex - 1) {
                buttonUp.visibility =
                    ConstraintLayout.VISIBLE
            }
            return index
        }
        return -1
    }
}