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
//                val body = BodyForGetPriceByNumberOfTrips(
//                    numberOfTripsId = 1,
//                    transports = [1,2,3],
//                    count = 5
//                )


                with(binding) {
                    backButtonInLimitedChooseScreen.setOnClickListener {
                        findNavController().popBackStack()
                    }
                    buttonUpInFirstLimitedItem.setOnClickListener {
                        incrementIndex(
                            buttonUpInFirstLimitedItem,
                            buttonDownInFirstLimitedItem,
                            textListInFirstLimitedItem,
                            currentIndexFirstItem,
                            numberOfTripsList
                        )
//                        if (currentIndexFirstItem < numberOfTripsList.size - 1) {
//                            currentIndexFirstItem++
//                            textListInFirstLimitedItem.text =
//                                numberOfTripsList[currentIndexFirstItem].toString()
//                            if (currentIndexFirstItem == 1) {
//                                buttonDownInFirstLimitedItem.visibility = ConstraintLayout.VISIBLE
//                            } else if (currentIndexFirstItem == numberOfTripsList.lastIndex) {
//                                buttonUpInFirstLimitedItem.visibility = ConstraintLayout.INVISIBLE
//                            }
//                        }
                    }
                    buttonDownInFirstLimitedItem.setOnClickListener {
                        decrementIndex(
                            buttonUpInFirstLimitedItem,
                            buttonDownInFirstLimitedItem,
                            textListInFirstLimitedItem,
                            currentIndexFirstItem,
                            numberOfTripsList
                        )
//                        if (currentIndexFirstItem <= numberOfTripsList.size - 1) {
//                            currentIndexFirstItem--
//                            textListInFirstLimitedItem.text =
//                                numberOfTripsList[currentIndexFirstItem].toString()
//                            if (currentIndexFirstItem == 0) {
//                                buttonDownInFirstLimitedItem.visibility = ConstraintLayout.INVISIBLE
//                            } else if (currentIndexFirstItem == numberOfTripsList.lastIndex - 1) {
//                                buttonUpInFirstLimitedItem.visibility = ConstraintLayout.VISIBLE
//                            }
//                        }
                    }
                    buttonUpInSecondLimitedItem.setOnClickListener {
                        if (currentIndexSecondItem < numberOfTripsListForMetro.size - 1) {
                            currentIndexSecondItem++
                            textListInSecondLimitedItem.text =
                                numberOfTripsListForMetro[currentIndexSecondItem].toString()
                            if (currentIndexSecondItem == 1) {
                                buttonDownInSecondLimitedItem.visibility = ConstraintLayout.VISIBLE
                            } else if (currentIndexSecondItem == numberOfTripsListForMetro.lastIndex) {
                                buttonUpInSecondLimitedItem.visibility = ConstraintLayout.INVISIBLE
                            }
                        }
                    }
                    buttonDownInSecondLimitedItem.setOnClickListener {
                        if (currentIndexSecondItem <= numberOfTripsListForMetro.size - 1) {
                            currentIndexSecondItem--
                            textListInSecondLimitedItem.text =
                                numberOfTripsListForMetro[currentIndexSecondItem].toString()
                            if (currentIndexSecondItem == 0) {
                                buttonDownInSecondLimitedItem.visibility =
                                    ConstraintLayout.INVISIBLE
                            } else if (currentIndexSecondItem == numberOfTripsListForMetro.lastIndex - 1) {
                                buttonUpInSecondLimitedItem.visibility = ConstraintLayout.VISIBLE
                            }
                        }
                    }
                    buttonUpInThirdLimitedItem.setOnClickListener {
                         incrementIndex(
                            buttonUpInThirdLimitedItem,
                            buttonDownInThirdLimitedItem,
                            textListInThirdLimitedItem,
                            currentIndexThirdItem,
                            numberOfTripsList
                        )
//                        if (currentIndexThirdItem < numberOfTripsList.size - 1) {
//                            currentIndexThirdItem++
//                            textListInThirdLimitedItem.text =
//                                numberOfTripsList[currentIndexThirdItem].toString()
//                            if (currentIndexThirdItem == 1) {
//                                buttonDownInThirdLimitedItem.visibility = ConstraintLayout.VISIBLE
//                            } else if (currentIndexThirdItem == numberOfTripsList.lastIndex) {
//                                buttonUpInThirdLimitedItem.visibility = ConstraintLayout.INVISIBLE
//                            }
//                        }
                    }
                    buttonDownInThirdLimitedItem.setOnClickListener {
                         decrementIndex(
                            buttonUpInThirdLimitedItem,
                            buttonDownInThirdLimitedItem,
                            textListInThirdLimitedItem,
                            currentIndexThirdItem,
                            numberOfTripsList
                        )
//                        if (currentIndexThirdItem <= numberOfTripsList.size - 1) {
//                            currentIndexThirdItem--
//                            textListInThirdLimitedItem.text =
//                                numberOfTripsList[currentIndexThirdItem].toString()
//                            if (currentIndexThirdItem == 0) {
//                                buttonDownInThirdLimitedItem.visibility = ConstraintLayout.INVISIBLE
//                            } else if (currentIndexThirdItem == numberOfTripsList.lastIndex - 1) {
//                                buttonUpInThirdLimitedItem.visibility = ConstraintLayout.VISIBLE
//                            }
//                        }
                    }
                }
            })
        }
    }


    private fun incrementIndex(
        buttonUp: MaterialButton,
        buttonDown: MaterialButton,
        textList: TextView,
        currentIndex: Int,
        list: List<Int>
    ){

        if (currentIndex < list.size - 1) {
            //currentIndex++
            textList.text = list[currentIndex].toString()
            if (currentIndex == 1) {
                buttonDown.visibility = ConstraintLayout.VISIBLE
            } else if (currentIndex == list.lastIndex) {
                buttonUp.visibility = ConstraintLayout.INVISIBLE
            }
        }
    }

    private fun decrementIndex(
        buttonUp: MaterialButton,
        buttonDown: MaterialButton,
        textList: TextView,
        currentIndex: Int,
        list: List<Int>
    ){
        if (currentIndex <= list.size - 1) {
            //currentIndex--
            textList.text = list[currentIndex].toString()
            if (currentIndex == 0) {
                buttonDown.visibility = ConstraintLayout.INVISIBLE
            } else if (currentIndex == list.lastIndex - 1) {
                buttonUp.visibility = ConstraintLayout.VISIBLE
            }
        }
    }
}