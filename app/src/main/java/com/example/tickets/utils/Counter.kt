package com.example.tickets.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tickets.R

class Counter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    init {
        initialize(attrs)
    }

    private fun setupView() {
        LayoutInflater.from(context).inflate(R.layout.counter_view, this, true)
    }

    private fun initialize(attrs: AttributeSet?) {
        setupView()
        val transportName: TextView = findViewById(R.id.limited_item_name)
        val transportIcon: ImageView = findViewById(R.id.limited_item_icon)
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.Counter)
        transportName.text =
            attributes.getString(R.styleable.Counter_limitedItemName)
        transportIcon.setImageDrawable(attributes.getDrawable(R.styleable.Counter_limitedIcon))
        attributes.recycle()

    }

}