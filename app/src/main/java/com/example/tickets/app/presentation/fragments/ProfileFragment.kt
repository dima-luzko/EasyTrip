package com.example.tickets.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.app.data.model.TransportInfo
import com.example.tickets.app.presentation.CardViewModel
import com.example.tickets.app.presentation.adapter.TransportInfoAdapter
import com.example.tickets.databinding.FragmentProfileBinding
import com.example.tickets.utils.visibleBottomNavigation
import com.simform.custombottomnavigation.SSCustomBottomNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModel<CardViewModel>()

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        val arg  = arguments?.getString("cardNumber")
//        outState.putString("thisNumber",arg)
//    }

    //    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        binding.personalCardNumber.text = savedInstanceState?.getString("thisNumber").toString()
//    }

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
        getTransactionsByCard()
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

    private fun getTransactionsByCard() {
        val cardId = arguments?.getInt("cardId")
        viewModel.getTransactionByCard(cardId!!)
        viewModel.transactionsByCard.observe(viewLifecycleOwner, Observer {
            with(binding.typeOfTransportsList){
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = TransportInfoAdapter(it)
                hasFixedSize()
            }
        })
    }
}