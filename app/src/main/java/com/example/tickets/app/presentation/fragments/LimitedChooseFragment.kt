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
                numberOfTrips.observe(viewLifecycleOwner, Observer {
                    textListInFirstLimitedItem.text = it.first().value.toString()
                    textListInThirdLimitedItem.text = it.first().value.toString()
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
            getTransportName()
            transportName.observe(viewLifecycleOwner, Observer
            { transportName ->
                val bus = transportName.bus
                val trolleybus = transportName.trolleybus
                val tram = transportName.tram
                val busExpress = transportName.busExpressShort
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
                    numberOfTripsViewModel.getNumberOfTripsListValue()
                )
                getFinalPrice()
            }
            buttonDownInFirstLimitedItem.setOnClickListener {
                currentIndexFirstItem = decrementIndex(
                    buttonUpInFirstLimitedItem,
                    buttonDownInFirstLimitedItem,
                    textListInFirstLimitedItem,
                    currentIndexFirstItem,
                    numberOfTripsViewModel.getNumberOfTripsListValue()
                )
                getFinalPrice()
            }

            buttonUpInSecondLimitedItem.setOnClickListener {
                currentIndexSecondItem = incrementIndex(
                    buttonUpInSecondLimitedItem,
                    buttonDownInSecondLimitedItem,
                    textListInSecondLimitedItem,
                    currentIndexSecondItem,
                    numberOfTripsViewModel.getNumberOfTripsListValueForMetro()
                )
                getFinalPrice()
            }
            buttonDownInSecondLimitedItem.setOnClickListener {
                currentIndexSecondItem = decrementIndex(
                    buttonUpInSecondLimitedItem,
                    buttonDownInSecondLimitedItem,
                    textListInSecondLimitedItem,
                    currentIndexSecondItem,
                    numberOfTripsViewModel.getNumberOfTripsListValueForMetro()
                )
                getFinalPrice()
            }

            buttonUpInThirdLimitedItem.setOnClickListener {
                currentIndexThirdItem = incrementIndex(
                    buttonUpInThirdLimitedItem,
                    buttonDownInThirdLimitedItem,
                    textListInThirdLimitedItem,
                    currentIndexThirdItem,
                    numberOfTripsViewModel.getNumberOfTripsListValue()
                )
                getFinalPrice()
            }
            buttonDownInThirdLimitedItem.setOnClickListener {
                currentIndexThirdItem = decrementIndex(
                    buttonUpInThirdLimitedItem,
                    buttonDownInThirdLimitedItem,
                    textListInThirdLimitedItem,
                    currentIndexThirdItem,
                    numberOfTripsViewModel.getNumberOfTripsListValue()
                )
                getFinalPrice()
            }
        }
    }

    private fun getFinalPrice() {
        with(numberOfTripsViewModel) {
            getPrice(
                body(
                    getIdByNumberOfTripList(currentIndexFirstItem),
                    firstTransportList,
                    firstTransportList.size
                ),
                body(
                    getIdByNumberOfTripListForMetro(currentIndexSecondItem),
                    secondTransportList,
                    secondTransportList.size
                ),
                body(
                    getIdByNumberOfTripList(currentIndexThirdItem),
                    thirdTransportList,
                    thirdTransportList.size
                )
            )
        }
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