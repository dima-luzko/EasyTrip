package com.example.tickets.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.data.CardNumber
import com.example.tickets.databinding.ErrorPopupWindowBinding
import com.example.tickets.databinding.FragmentLoginBinding
import com.example.tickets.utils.goneBottomNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var errorDialogBinding: ErrorPopupWindowBinding

    override fun onStart() {
        super.onStart()
        activity?.let { goneBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        equalsCardNumber(view)
    }

    private fun equalsCardNumber(view: View) {
        val bundle = Bundle()
        binding.buttonOk.setOnClickListener {
            val getInputCardNumber = binding.inputCardNumber.text.toString()
            when {
                getInputCardNumber.isEmpty() -> {
                    showErrorDialog(
                        getString(R.string.card_number_cannot_be_empty),
                        getString(R.string.button_ok)
                    )
                }
                getInputCardNumber in cardList.map { it.cardNumber }.toString() -> {
                    hideSystemUI()
                    bundle.putString("cardNumber", getInputCardNumber)
                    findNavController().navigate(
                        R.id.action_loginFragment_to_profileFragment,
                        bundle
                    )
                }
                else -> {
                    showErrorDialog(
                        getString(R.string.no_card_number),
                        getString(R.string.button_try_again)
                    )
                }
            }
        }
    }

    private fun showErrorDialog(errorMessage: String, buttonText: String) {
        val dialog = context?.let { Dialog(it) }
        errorDialogBinding = ErrorPopupWindowBinding.inflate(layoutInflater)
        with(dialog) {
            this?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this?.setContentView(errorDialogBinding.root)
            this?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        errorDialogBinding.errorMessage.text = errorMessage
        with(errorDialogBinding.buttonTryAgain) {
            this.text = buttonText
            this.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(600)
                    dialog?.dismiss()
                }
            }
        }
        dialog?.setCancelable(false)
        dialog?.show()
    }

    private val cardList = listOf(
        CardNumber(
            cardNumber = 1111222233334444
        ),
        CardNumber(
            cardNumber = 2222333344445555
        ),
        CardNumber(
            cardNumber = 3333444455556666
        )
    )


    @Suppress("DEPRECATION")
    private fun hideSystemUI() {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) {
            val flags = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            activity?.window?.decorView?.systemUiVisibility = flags
            (activity as? AppCompatActivity)?.supportActionBar?.hide()
        }
    }
}