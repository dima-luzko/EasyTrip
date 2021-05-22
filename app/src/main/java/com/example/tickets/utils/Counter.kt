package com.example.tickets.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tickets.R
import com.example.tickets.data.NumberOfTrips
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
            if (limitedItemName.text == "Метро") {
                buttonUp.setOnClickListener {
                    incrementIndex(newNumberOfTripsList)
                }
                buttonDown.setOnClickListener {
                    decrementIndex(newNumberOfTripsList)
                }
            } else {
                buttonUp.setOnClickListener {
                    incrementIndex(numberOfTripsList)
                }
                buttonDown.setOnClickListener {
                    decrementIndex(numberOfTripsList)
                }
            }
        }
        attributes.recycle()
    }

    private val newNumberOfTripsList = numberOfTripsList.filterIndexed { index, _ ->
        index != 1 && index != 2 && index != 3 && index != 6 && index != 11
    }

    private fun incrementIndex(tripList: List<NumberOfTrips>) {
        if (currentIndex < tripList.size - 1) {
            currentIndex++
            counterBinding.textList.text = tripList[currentIndex].number.toString()
            if (currentIndex == 1) {
                counterBinding.buttonDown.visibility = VISIBLE
            } else if (currentIndex == tripList.lastIndex) {
                counterBinding.buttonUp.visibility = INVISIBLE
            }
        }
    }

    private fun decrementIndex(tripList: List<NumberOfTrips>) {
        if (currentIndex <= tripList.size - 1) {
            currentIndex--
            counterBinding.textList.text = tripList[currentIndex].number.toString()
            if (currentIndex == 0) {
                counterBinding.buttonDown.visibility = INVISIBLE
            } else if (currentIndex == tripList.lastIndex - 1) {
                counterBinding.buttonUp.visibility = VISIBLE
            }
        }
    }
}