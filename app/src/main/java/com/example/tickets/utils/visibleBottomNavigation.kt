package com.example.tickets.utils

import android.app.Activity
import android.view.View
import com.example.tickets.R
import com.simform.custombottomnavigation.SSCustomBottomNavigation

fun visibleBottomNavigation(activity : Activity){
    val bottomNavigation = activity.findViewById<SSCustomBottomNavigation>(R.id.bottom_navigation)
    bottomNavigation?.visibility = View.VISIBLE
}