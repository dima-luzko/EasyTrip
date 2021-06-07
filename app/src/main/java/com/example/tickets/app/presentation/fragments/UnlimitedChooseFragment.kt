package com.example.tickets.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.app.presentation.adapter.UnlimitedTransportInfoAdapter
import com.example.tickets.app.presentation.viewModel.TransportViewModel
import com.example.tickets.databinding.FragmentUnlimitedChooseBinding
import com.example.tickets.utils.goneBottomNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnlimitedChooseFragment : Fragment() {

    private lateinit var binding: FragmentUnlimitedChooseBinding
    private val viewModel by viewModel<TransportViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnlimitedChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            numberOfDaysInUnlimitedChooseScreen.text = arguments?.getString("numberOfDays")
            backButtonInUnlimitedChooseScreen.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        equalsNumberOfDays(view)
        addToUnlimitedTransportInfoRecyclerView(view)
    }

    private fun equalsNumberOfDays(view: View) {
        val days = view.findViewById<TextView>(R.id.text_days)
        if (binding.numberOfDaysInUnlimitedChooseScreen.text == "1") {
            days.text = getString(R.string.day)
        }
    }

    private fun addToUnlimitedTransportInfoRecyclerView(view: View) {
        viewModel.getTransport()
        viewModel.transport.observe(viewLifecycleOwner, Observer {
            with(binding.unlimitedTransportList) {
                layoutManager = GridLayoutManager(
                    context,
                    2,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = UnlimitedTransportInfoAdapter(it) {

                }
                hasFixedSize()
            }
        })
    }
}