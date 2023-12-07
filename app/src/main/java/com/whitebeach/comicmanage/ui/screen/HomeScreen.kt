package com.whitebeach.comicmanage.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.whitebeach.comicmanage.PagerSample
import com.whitebeach.comicmanage.SampleDetailScreen
import com.whitebeach.comicmanage.ScrollableTabRowSample
import com.whitebeach.comicmanage.ui.theme.ComicManageTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var tabIndex by remember { mutableIntStateOf(0) }
    val titles = listOf("Tab 0", "Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { titles.size }
    val navControllerX = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ScrollableTabRowSample(
            tabIndex = tabIndex,
            operation = { tabIndex = it },
            titles = titles,
            coroutineScope = coroutineScope,
            pagerState = pagerState
        )

        NavHost(navController = navControllerX, startDestination = "PagerSample") {
            composable(route = "PagerSample") {
                PagerSample(
                    pageCount = titles.size,
                    state = pagerState,
                    navController = navControllerX
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
                    navController = navControllerX
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