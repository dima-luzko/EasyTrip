package com.example.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tickets.R
import com.example.tickets.utils.goneBottomNavigation
import com.google.android.material.button.MaterialButton

class LimitedChooseFragment : Fragment() {

    private lateinit var backButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_limited_choose, container, false)
    }

    override fun onStart() {
        super.onStart()
        backButton = requireView().findViewById(R.id.back_button_in_limited_choose_screen)
        backButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_limitedChooseFragment_to_priceFragment,
                null
            )
        )
    }

    override fun onStop() {
        super.onStop()
        backButton.setOnClickListener(null)
    }
}