package com.example.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tickets.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        requireView().findViewById<ImageButton>(R.id.button_ok)
            .setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_loginFragment_to_profileFragment,
                    null
                )
            )

    }

}