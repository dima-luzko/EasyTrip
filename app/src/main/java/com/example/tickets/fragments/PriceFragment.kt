package com.example.tickets.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.adapter.NumberOfDaysAdapter
import com.example.tickets.data.NumberOfDays
import com.example.tickets.databinding.ActivityMainBinding
import com.example.tickets.databinding.FragmentPriceBinding
import com.example.tickets.databinding.UnlimitedTripsPopupWindowBinding
import com.example.tickets.utils.visibleBottomNavigation
import com.simform.custombottomnavigation.SSCustomBottomNavigation


class PriceFragment : Fragment() {

    private lateinit var binding: FragmentPriceBinding
    private lateinit var unlimitedTripsDialogBinding: UnlimitedTripsPopupWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { visibleBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentPriceBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<SSCustomBottomNavigation>(R.id.bottom_navigation)
            .setOnClickMenuListener { menuItem ->
                if (menuItem.id == 1) {
                    findNavController().navigate(R.id.action_priceFragment_to_profileFragment)
                    return@setOnClickMenuListener
                }
            }

        binding.limitedTripsButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_priceFragment_to_limitedChooseFragment,
                null
            )
        )

        binding.unlimitedTripsButton.setOnClickListener {
            showPopupDialog()
        }
    }

    private fun showPopupDialog() {
        val bundle = Bundle()
        val dialog = context?.let { Dialog(it) }
        unlimitedTripsDialogBinding = UnlimitedTripsPopupWindowBinding.inflate(layoutInflater)

        with(dialog) {
            this?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this?.setContentView(unlimitedTripsDialogBinding.root)
            this?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        with(unlimitedTripsDialogBinding.numberOfDaysList) {
            this.layoutManager = GridLayoutManager(
                context,
                3,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = NumberOfDaysAdapter(numberOfDaysList()) {
                bundle.putString("numberOfDays", it.numberOfDays.toString())
                findNavController().navigate(
                    R.id.action_priceFragment_to_unlimitedChooseFragment,
                    bundle
                )
                dialog?.dismiss()
            }
            this.hasFixedSize()
        }
        dialog?.show()
    }

    private fun numberOfDaysList() = listOf(
        NumberOfDays(
            numberOfDays = 1
        ),
        NumberOfDays(
            numberOfDays = 2
        ),
        NumberOfDays(
            numberOfDays = 3
        ),
        NumberOfDays(
            numberOfDays = 5
        ),
        NumberOfDays(
            numberOfDays = 10
        ),
        NumberOfDays(
            numberOfDays = 15
        ),
        NumberOfDays(
            numberOfDays = 20
        ),
        NumberOfDays(
            numberOfDays = 30
        ),
        NumberOfDays(
            numberOfDays = 90
        )
    )

}
