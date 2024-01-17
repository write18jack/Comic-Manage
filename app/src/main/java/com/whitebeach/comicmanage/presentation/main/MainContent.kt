package com.whitebeach.comicmanage.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.whitebeach.comicmanage.app.component.AppDrawerContent
import com.whitebeach.comicmanage.app.component.DrawerParams
import com.whitebeach.comicmanage.app.component.MainNavOption
import com.whitebeach.comicmanage.presentation.ad.AdScreen
import com.whitebeach.comicmanage.presentation.applist.AppListScreen
import com.whitebeach.comicmanage.presentation.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    Surface {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawerContent(
                    drawerState = drawerState,
                    menuItems = DrawerParams.drawerButtons,
                    defaultPick = MainNavOption.HomeScreen,
                    onClick = {

                    }
                )
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Comic Manage") },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    navController.navigate(Screens.AppList.route){}
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = null)
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        BottomNavigationItem().bottomNavigationItems()
                            .forEachIndexed { _, navigationItem ->
                                NavigationBarItem(
                                    selected = navigationItem.route == currentDestination?.route,
                                    icon = {
                                        Icon(
                                            navigationItem.icon,
                                            contentDescription = ""
                                        )
                                    },
                                    onClick = {
                                        navController.navigate(navigationItem.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                    }
                }
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = Screens.Home.route,
                    modifier = Modifier.padding(paddingValues = paddingValues)
                ) {
                    composable(route = Screens.Home.route) {
                        HomeScreen(navController)
                    }
                    composable(route = Screens.AppList.route) {
                        AppListScreen(navController)
                    }
                    composable(route = Screens.Setting.route) {
                        AdScreen(navController)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContent()
}