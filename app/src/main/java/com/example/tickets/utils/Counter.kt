package com.example.tickets.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tickets.R
import com.example.tickets.databinding.CounterViewBinding

class Counter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private lateinit var counterBinding: CounterViewBinding
    private var currentIndex = 0

    init {
        initialize(attrs)
    }

    private fun setupView() {
        counterBinding = CounterViewBinding.inflate(LayoutInflater.from(context), this)
    }

    private fun initialize(attrs: AttributeSet?) {
        setupView()
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.Counter)

        with(counterBinding) {
            limitedItemName.text =
                attributes.getString(R.styleable.Counter_limitedItemName)
            limitedItemIcon.setImageDrawable(attributes.getDrawable(R.styleable.Counter_limitedIcon))
            buttonUp.setOnClickListener {
                incrementIndex()
            }
            buttonDown.setOnClickListener {
               decrementIndex()
            }
        }
        attributes.recycle()
    }

    private fun incrementIndex() {
        if (currentIndex < numberOfTripsList.size - 1) {
            currentIndex++
            counterBinding.textList.text = numberOfTripsList[currentIndex].number.toString()
            if (currentIndex == 1) {
                counterBinding.buttonDown.visibility = VISIBLE
            } else if (currentIndex == numberOfTripsList.lastIndex) {
                counterBinding.buttonUp.visibility = INVISIBLE
            }
        }
    }

    private fun decrementIndex() {
        if (currentIndex <= numberOfTripsList.size - 1) {
            currentIndex--
            counterBinding.textList.text = numberOfTripsList[currentIndex].number.toString()
            if (currentIndex == 0) {
                counterBinding.buttonDown.visibility = INVISIBLE
            } else if (currentIndex == numberOfTripsList.lastIndex - 1) {
                counterBinding.buttonUp.visibility = VISIBLE
            }
        }
    }
}