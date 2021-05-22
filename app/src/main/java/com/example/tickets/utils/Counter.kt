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

    init {
        initialize(attrs)
    }

    private fun setupView() {
        counterBinding = CounterViewBinding.inflate(LayoutInflater.from(context), this)
    }

    private fun initialize(attrs: AttributeSet?) {
        var currentIndex = -1
        setupView()
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.Counter)

        with(counterBinding) {
            limitedItemName.text =
                attributes.getString(R.styleable.Counter_limitedItemName)
            limitedItemIcon.setImageDrawable(attributes.getDrawable(R.styleable.Counter_limitedIcon))
            buttonUp.setOnClickListener {
                if (currentIndex < numberOfTripsArray.size - 1) {
                    currentIndex++
                    counterBinding.textList.text = numberOfTripsArray[currentIndex].toString()
                }
            }
            buttonDown.setOnClickListener {
                if (currentIndex <= numberOfTripsArray.size - 1) {
                    currentIndex--
                    counterBinding.textList.text = numberOfTripsArray[currentIndex].toString()
                }
            }
        }
        attributes.recycle()
    }


    private val numberOfTripsArray = intArrayOf(1, 3, 7, 10, 20, 25, 30, 40, 50, 60, 100)

    private fun numberOfTripsList() = listOf(
        NumberOfTrips(
            number = 1
        ),
        NumberOfTrips(
            number = 3
        ),
        NumberOfTrips(
            number = 7
        ),
        NumberOfTrips(
            number = 10
        ),
        NumberOfTrips(
            number = 20
        ),
        NumberOfTrips(
            number = 25
        ),
        NumberOfTrips(
            number = 30
        ),
        NumberOfTrips(
            number = 40
        ),
        NumberOfTrips(
            number = 50
        ),
        NumberOfTrips(
            number = 60
        ),
        NumberOfTrips(
            number = 100
        )
    )

}