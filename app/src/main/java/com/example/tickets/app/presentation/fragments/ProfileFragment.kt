package com.example.tickets.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.app.presentation.adapter.TransportInfoAdapter
import com.example.tickets.app.presentation.viewModel.CardViewModel
import com.example.tickets.databinding.FragmentProfileBinding
import com.example.tickets.utils.visibleBottomNavigation
import com.simform.custombottomnavigation.SSCustomBottomNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val cardViewModel by viewModel<CardViewModel>()

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
        with(binding) {
            personalCardNumber.text = arguments?.getString("cardNumber")
            buttonExit.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
        }
        getTransactionsByCard()

        requireActivity().findViewById<SSCustomBottomNavigation>(R.id.bottom_navigation)
            .setOnClickMenuListener { menuItem ->
                if (menuItem.id == 2) {
                    findNavController().navigate(R.id.action_profileFragment_to_priceFragment)
                    return@setOnClickMenuListener
                }
            }
    }

    private fun getTransactionsByCard() {
        val cardId = arguments?.getInt("cardId")
        cardViewModel.getTransactionByCard(cardId!!)
        cardViewModel.transactionsByCard.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.noCardTransactions.visibility = View.VISIBLE
            } else {
                with(binding.typeOfTransportsList) {
                    layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    adapter = TransportInfoAdapter(it)
                    hasFixedSize()
                }
            }
        })
    }
}