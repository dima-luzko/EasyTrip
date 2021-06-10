package com.example.tickets.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfDays
import com.example.tickets.app.presentation.adapter.UnlimitedTransportInfoAdapter
import com.example.tickets.app.presentation.viewModel.NumberOfDaysViewModel
import com.example.tickets.app.presentation.viewModel.TransportViewModel
import com.example.tickets.databinding.FragmentUnlimitedChooseBinding
import com.example.tickets.utils.goneBottomNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnlimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentUnlimitedChooseBinding
    private val transportViewModel by viewModel<TransportViewModel>()
    private val priceViewModel by viewModel<NumberOfDaysViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnlimitedChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        activity?.let { goneBottomNavigation(it) }
//        transportViewModel.getTransport()
//        transportViewModel.transport.observe(viewLifecycleOwner, Observer { transport ->
//            with(binding.unlimitedTransportList) {
//                layoutManager = GridLayoutManager(
//                    context,
//                    2,
//                    LinearLayoutManager.VERTICAL,
//                    false
//                )
//                adapter = UnlimitedTransportInfoAdapter(transport)
//                hasFixedSize()
//            }
//            val transportsList = arrayListOf(UnlimitedTransportInfoAdapter(transport).getChangeTransport())
//            val body = BodyForGetPriceByNumberOfDays(
//                numberOfDaysId = arguments?.getInt("numberOfDaysId")!!.toInt(),
//                transports = transportList,
//                count = transportList.size
//            )
//
//
//            with(binding) {
//                numberOfDaysInUnlimitedChooseScreen.text = arguments?.getString("numberOfDays")
//                backButtonInUnlimitedChooseScreen.setOnClickListener {
//                    findNavController().popBackStack()
//                }
//                buttonGetPrice.setOnClickListener {
//                    priceViewModel.getPrice(body)
//                    priceViewModel.price.observe(viewLifecycleOwner, Observer {
////                        Toast.makeText(context,it.price.toString(),Toast.LENGTH_LONG).show()
//                        Toast.makeText(context,transportsList.size.toString(),Toast.LENGTH_LONG).show()
//                    })
//                }
//            }
//
//        })

//
//
//        equalsNumberOfDays(view)
//        //addToUnlimitedTransportInfoRecyclerView()
//        //getPrice()
//
//    }

    private fun equalsNumberOfDays(view: View) {
        val days = view.findViewById<TextView>(R.id.text_days)
        if (binding.numberOfDaysInUnlimitedChooseScreen.text == "1") {
            days.text = getString(R.string.day)
        }
    }

//    private fun addToUnlimitedTransportInfoRecyclerView() {
//        transportViewModel.getTransport()
//        transportViewModel.transport.observe(viewLifecycleOwner, Observer {
//            with(binding.unlimitedTransportList) {
//                layoutManager = GridLayoutManager(
//                    context,
//                    2,
//                    LinearLayoutManager.VERTICAL,
//                    false
//                )
//                adapter = UnlimitedTransportInfoAdapter(it)
//                hasFixedSize()
//            }
//            val transportsList = arrayListOf(UnlimitedTransportInfoAdapter(it).getChangeTransport())
//        })
//
//    }

//    private fun getPrice() {
//        priceViewModel.getPrice(body)
//        priceViewModel.price.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(context,it.price.toString(),Toast.LENGTH_LONG).show()
//        })
//    }

    private val transportList = arrayListOf(1,2,6)


//    private val body = BodyForGetPriceByNumberOfDays(
//        numberOfDaysId = arguments?.getInt("numberOfDaysId")!!.toInt(),
//        transports = transportList,
//        count = transportList.size
//    )
}