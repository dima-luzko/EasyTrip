package com.example.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.databinding.FragmentLimitedChooseBinding
import com.example.tickets.utils.goneBottomNavigation

class LimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentLimitedChooseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
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
        binding.backButtonInLimitedChooseScreen.setOnClickListener {
            findNavController().navigate(R.id.action_limitedChooseFragment_to_priceFragment)
        }
    }
}