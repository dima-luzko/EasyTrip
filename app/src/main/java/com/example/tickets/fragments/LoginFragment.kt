package com.example.tickets.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.data.CardNumber
import com.example.tickets.utils.goneBottomNavigation

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
                        context,
                        "Номер не может быть пустым!",
                        "Ок"
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
                        context,
                        "Карты с таким номером не существует!",
                        "Попробовать снова"
                    )
                }
            }
        }
    }

    private fun showErrorDialog(
        context: Context?,
        message: String,
        positiveButtonText: String
    ) {
        val builder = AlertDialog.Builder(context)
        hideSystemUI()
        with(builder) {
            setTitle("Ошибка")
            setMessage(message)
            setPositiveButton(positiveButtonText) { dialog, _ -> dialog.cancel() }
            setCancelable(false)
            create()
            show()
        }
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