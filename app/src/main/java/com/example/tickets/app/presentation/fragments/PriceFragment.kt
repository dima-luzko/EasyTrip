package com.example.tickets.app.presentation.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickets.R
import com.example.tickets.app.presentation.adapter.NumberOfDaysAdapter
import com.example.tickets.app.presentation.viewModel.NumberOfDaysViewModel
import com.example.tickets.databinding.FragmentPriceBinding
import com.example.tickets.databinding.UnlimitedTripsPopupWindowBinding
import com.example.tickets.utils.visibleBottomNavigation
import com.simform.custombottomnavigation.SSCustomBottomNavigation
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PriceFragment : Fragment() {

    private lateinit var binding: FragmentPriceBinding
    private lateinit var unlimitedTripsDialogBinding: UnlimitedTripsPopupWindowBinding
    private val numberOfDaysViewModel by viewModel<NumberOfDaysViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { visibleBottomNavigation(it) }
        requireActivity().findViewById<SSCustomBottomNavigation>(R.id.bottom_navigation)
            .setOnClickMenuListener { menuItem ->
                if (menuItem.id == 1) {
                    findNavController().popBackStack()
                    //findNavController().navigate(R.id.action_priceFragment_to_profileFragment)
                    return@setOnClickMenuListener
                }
            }

        with(binding.limitedTripsButton) {
            setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    findNavController().navigate(R.id.action_priceFragment_to_limitedChooseFragment)
                }
                playAnimation()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    pauseAnimation()
                }
            }
        }

        with(binding.unlimitedTripsButton) {
            setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    showPopupDialog()
                }
                playAnimation()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    pauseAnimation()
                }
            }
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

        with(numberOfDaysViewModel) {
            getNumberOfDays()
            numberOfDays.observe(viewLifecycleOwner, Observer { numberOfDays ->
                with(unlimitedTripsDialogBinding.numberOfDaysList) {
                    this.layoutManager = GridLayoutManager(
                        context,
                        3,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    this.adapter = NumberOfDaysAdapter(numberOfDays) {
                        with(bundle) {
                            putString("numberOfDays", it.value.toString())
                            putInt("numberOfDaysId", it.id)
                        }
                        findNavController().navigate(
                            R.id.action_priceFragment_to_unlimitedChooseFragment,
                            bundle
                        )
                        dialog?.dismiss()
                    }
                    this.hasFixedSize()
                }
            })
        }
        dialog?.show()
    }
}
