package com.whitebeach.comicmanage.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.whitebeach.comicmanage.app.theme.ComicManageTheme
import com.whitebeach.comicmanage.presentation.home.view.HomeHorizontalPager
import com.whitebeach.comicmanage.presentation.home.view.HomeScrollableTabRow
import com.whitebeach.comicmanage.presentation.home.view.SampleDetailScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var tabIndex by remember { mutableIntStateOf(0) }
    val titles = listOf("Tab 0", "Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { titles.size }
    val navControllerInHomeScreen = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HomeScrollableTabRow(
            tabIndex = tabIndex,
            operation = { tabIndex = it },
            titles = titles,
            coroutineScope = coroutineScope,
            pagerState = pagerState
        )

        NavHost(navController = navControllerInHomeScreen, startDestination = "PagerSample") {
            composable(route = "PagerSample") {
                HomeHorizontalPager(
                    state = pagerState,
                    navController = navControllerInHomeScreen
                )
            }
            composable(
                route = "SampleDetailScreen/{index}",
                arguments = listOf(
                    navArgument(name = "index") {
                        type = NavType.IntType
                    }
                )
            ) {index ->
                SampleDetailScreen(
                    itemIndex = index.arguments?.getInt("index"),
                    navController = navControllerInHomeScreen
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    ComicManageTheme {
        HomeScreen(navController)
    }
}