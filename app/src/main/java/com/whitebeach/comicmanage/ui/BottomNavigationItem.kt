package com.whitebeach.comicmanage.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                icon = Icons.Filled.AddCircle,
                route = Screens.AppList.route
            ),
            BottomNavigationItem(
                icon = Icons.Filled.List,
                route = Screens.Setting.route
            ),
        )
    }
}