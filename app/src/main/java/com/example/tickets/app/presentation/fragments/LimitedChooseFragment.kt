package com.example.tickets.app.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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


class LimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentLimitedChooseBinding
    private var currentIndexFirstItem = 0
    private var currentIndexSecondItem = 0
    private var currentIndexThirdItem = 0
    private val numberOfTripsViewModel by viewModel<NumberOfTripsViewModel>()
    private val transportViewModel by viewModel<TransportViewModel>()
    private var firstPrice: Double = 0.0
    private var secondPrice: Double = 0.0
    private var thirdPrice: Double = 0.0
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
        handleClick()
        setTransportName()
    }

    @SuppressLint("SetTextI18n")
    private fun setTransportName() {
        with(transportViewModel) {
            getTransport()
            transport.observe(viewLifecycleOwner, Observer
            { transport ->
                val firstTransport = transport.map { it.transportName }[0]
                val secondTransport = transport.map { it.transportName }[1]
                val thirdTransport = transport.map { it.transportName }[2]
                val fourTransport = transport.map { it.transportName }[3].substring(0, 12)
                val fiveTransport = transport.map { it.transportName }[4]
                with(binding) {
                    nameInFirstLimitedItem.text =
                        "$firstTransport - $secondTransport - $thirdTransport"
                    nameInSecondLimitedItem.text = fiveTransport
                    nameInThirdLimitedItem.text = "$fourTransport.\""
                }
            })
        }
    }

    private fun handleClick() {
        with(numberOfTripsViewModel) {
            getNumberOfTrips()
            numberOfTrips.observe(viewLifecycleOwner, Observer { numberOfTrips ->

                val numberOfTripsList = numberOfTrips.map { it.value }
                val numberOfTripsListForMetro =
                    numberOfTrips.map { it.value }.filterIndexed { index, _ ->
                        index != 1 && index != 2 && index != 3 && index != 6 && index != 11
                    }

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
                            numberOfTripsListForMetro
                        )
//                        getPrice(currentIndexSecondItem, secondTransportList)
//                        price.observe(viewLifecycleOwner, Observer { countPrice ->
//                            secondPrice = countPrice.price
//                        })
                    }
                    buttonDownInSecondLimitedItem.setOnClickListener {
                        currentIndexSecondItem = decrementIndex(
                            buttonUpInSecondLimitedItem,
                            buttonDownInSecondLimitedItem,
                            textListInSecondLimitedItem,
                            currentIndexSecondItem,
                            numberOfTripsListForMetro
                        )
//                        getPrice(currentIndexSecondItem, secondTransportList)
//                        price.observe(viewLifecycleOwner, Observer { countPrice ->
//                            secondPrice = countPrice.price
//                        })
                    }


                    buttonUpInThirdLimitedItem.setOnClickListener {
                       currentIndexThirdItem = incrementIndex(
                           buttonUpInThirdLimitedItem,
                           buttonDownInThirdLimitedItem,
                           textListInThirdLimitedItem,
                           currentIndexThirdItem,
                           numberOfTripsList
                       )
//                        getPrice(currentIndexThirdItem, thirdTransportList)
//                        price.observe(viewLifecycleOwner, Observer { countPrice ->
//                            thirdPrice = countPrice.price
//                        })
                    }
                    buttonDownInThirdLimitedItem.setOnClickListener {
                        currentIndexThirdItem = decrementIndex(
                            buttonUpInThirdLimitedItem,
                            buttonDownInThirdLimitedItem,
                            textListInThirdLimitedItem,
                            currentIndexThirdItem,
                            numberOfTripsList
                        )
//                        getPrice(currentIndexThirdItem, thirdTransportList)
//                        price.observe(viewLifecycleOwner, Observer { countPrice ->
//                            thirdPrice = countPrice.price
//
//                        })
                    }

                    val finalPrice = firstPrice + thirdPrice

                    buttonGetPriceLimitedScreen.setOnClickListener {
                        Toast.makeText(context, finalPrice.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun getPrice(index: Int, transportsList: ArrayList<Int>) {
        with(numberOfTripsViewModel) {
            getPrice(
                body(
                    id = index + 1,
                    transports = transportsList,
                    count = transportsList.size
                )
            )
        }
    }

    private fun body(
        id: Int,
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