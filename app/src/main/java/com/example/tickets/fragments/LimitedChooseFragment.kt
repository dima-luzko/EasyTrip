package com.example.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.databinding.FragmentLimitedChooseBinding
import com.example.tickets.utils.goneBottomNavigation
import com.example.tickets.utils.numberOfTripsList

class LimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentLimitedChooseBinding
    private var currentIndex = 0

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
        with(binding){
            backButtonInLimitedChooseScreen.setOnClickListener {
                findNavController().navigate(R.id.action_limitedChooseFragment_to_priceFragment)
            }
            buttonUpInFirstLimitedItem.setOnClickListener {
                incrementIndex()
            }
            buttonDownInFirstLimitedItem.setOnClickListener {
                decrementIndex()
            }
        }

    }

    private fun incrementIndex() {
        if (currentIndex < numberOfTripsList.size - 1) {
            currentIndex++
            binding.textListInFirstLimitedItem.text = numberOfTripsList[currentIndex].number.toString()
            if (currentIndex == 1) {
                binding.buttonDownInFirstLimitedItem.visibility = ConstraintLayout.VISIBLE
            } else if (currentIndex == numberOfTripsList.lastIndex) {
                binding.buttonUpInFirstLimitedItem.visibility = ConstraintLayout.INVISIBLE
            }
        }
    }

    private fun decrementIndex() {
        if (currentIndex <= numberOfTripsList.size - 1) {
            currentIndex--
            binding.textListInFirstLimitedItem.text = numberOfTripsList[currentIndex].number.toString()
            if (currentIndex == 0) {
                binding.buttonDownInFirstLimitedItem.visibility = ConstraintLayout.INVISIBLE
            } else if (currentIndex == numberOfTripsList.lastIndex - 1) {
                binding.buttonUpInFirstLimitedItem.visibility = ConstraintLayout.VISIBLE
            }
        }
    }
}