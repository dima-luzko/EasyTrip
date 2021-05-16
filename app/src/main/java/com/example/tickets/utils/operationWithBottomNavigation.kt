package com.example.tickets.utils

import android.app.Activity
import android.view.View
import com.example.tickets.R
import com.simform.custombottomnavigation.SSCustomBottomNavigation

private lateinit var bottomNavigation: SSCustomBottomNavigation

fun visibleBottomNavigation(activity: Activity) {
    bottomNavigation = activity.findViewById(R.id.bottom_navigation)
    bottomNavigation.visibility = View.VISIBLE
}

fun goneBottomNavigation(activity: Activity) {
   bottomNavigation = activity.findViewById(R.id.bottom_navigation)
    bottomNavigation.visibility = View.GONE
}