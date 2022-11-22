package org.mdeveloperz.common.ui.navigation

import androidx.navigation.NavController

interface UiDestination {
    fun navigate(navController: NavController)
}
