package com.whitebeach.comicmanage.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect
import androidx.navigation.compose.rememberNavController
import com.microsoft.device.dualscreen.twopanelayout.TwoPaneLayout
import com.microsoft.device.dualscreen.twopanelayout.TwoPaneLayoutNav
import com.microsoft.device.dualscreen.twopanelayout.TwoPaneMode
import com.microsoft.device.dualscreen.twopanelayout.twopanelayoutnav.composable
import com.microsoft.device.dualscreen.windowstate.WindowState
import com.microsoft.device.dualscreen.windowstate.rememberWindowState
import com.whitebeach.comicmanage.app.theme.ComicManageTheme

class MainActivity : ComponentActivity() {
    private lateinit var windowState: WindowState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            windowState = rememberWindowState()

            ComicManageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // MainContent()
                    MainPage(windowState = windowState)
                }
            }
        }
    }
}

@Composable
fun MainPage(windowState: WindowState) {

}

//@Composable
//fun NavigationRailAppContent(
//    isDualScreen: Boolean,
//    isDualPortrait: Boolean,
//    isDualLandscape: Boolean,
//    foldIsOccluding: Boolean,
//    foldBoundsDp: DpRect,
//    windowHeight: Dp,
//    imageId: Int?,
//    updateImageId: (Int?) -> Unit
//) {
//    val navController = rememberNavController()
//
//    TwoPaneLayoutNav(
//        paneMode = TwoPaneMode.HorizontalSingle,
//        navController = navController,
//        singlePaneStartDestination = GallerySections.PLANTS.route,
//        pane1StartDestination = GallerySections.PLANTS.route,
//        pane2StartDestination = ITEM_DETAIL_ROUTE
//    ) {
//        navDestinations.map { section ->
//            composable(section.route) {
//                GalleryViewWithTopBar(
//                    section = section,
//                    horizontalPadding = GALLERY_HORIZ_PADDING,
//                    navController = navController,
//                    isDualScreen = isDualScreen,
//                    imageId = imageId,
//                    updateImageId = updateImageId
//                )
//            }
//        }
//        composable(ITEM_DETAIL_ROUTE) {
//            ItemDetailViewWithTopBar(
//                isDualPortrait = isDualPortrait,
//                isDualLandscape = isDualLandscape,
//                foldIsOccluding = foldIsOccluding,
//                foldBoundsDp = foldBoundsDp,
//                windowHeight = windowHeight,
//                imageId = imageId,
//                updateImageId = updateImageId,
//                navController = navController
//            )
//        }
//    }
//}


