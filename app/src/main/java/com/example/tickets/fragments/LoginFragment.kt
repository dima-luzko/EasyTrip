package com.example.tickets.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.data.CardNumber
import com.example.tickets.utils.goneBottomNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    override fun onStart() {
        super.onStart()
        activity?.let { goneBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        equalsCardNumber(view)
    }

    private fun equalsCardNumber(view: View) {
        val bundle = Bundle()
        val inputCardNumber = view.findViewById<EditText>(R.id.input_card_number)
        view.findViewById<ImageButton>(R.id.button_ok).setOnClickListener {
            val getInputCardNumber = inputCardNumber?.text.toString()
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
        with(dialog) {
            this?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this?.setContentView(R.layout.error_popup_window)
            this?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog?.findViewById<TextView>(R.id.error_message)?.text = errorMessage
        val repeatButton = dialog?.findViewById<AppCompatButton>(R.id.button_try_again)
        with(repeatButton) {
            this?.text = buttonText
            this?.setOnClickListener {
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