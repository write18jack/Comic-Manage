package com.whitebeach.comicmanage.presentation.main

sealed class Screens(val route : String) {
    object Home : Screens("home_route")
    object AppList : Screens("appList_route")
    object Setting : Screens("setting_route")
}