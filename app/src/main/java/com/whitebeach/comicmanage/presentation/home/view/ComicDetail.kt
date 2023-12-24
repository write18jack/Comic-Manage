package com.whitebeach.comicmanage.presentation.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.whitebeach.comicmanage.app.theme.ComicManageTheme
import com.whitebeach.comicmanage.presentation.applist.ComicAppList
import com.whitebeach.comicmanage.presentation.home.view.HomeHorizontalPager
import com.whitebeach.comicmanage.presentation.home.view.HomeScrollableTabRow
import com.whitebeach.comicmanage.presentation.home.view.SampleDetailScreen

@OptIn
@Composable
fun ComicDetail(
    itemIndex: Int?,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "buck"
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(100.dp)
                .background(
                    Color.White,
//                    RoundedCornerShape(9.dp)
                )
        ) {
            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it },
//                label = { Text("") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            )
            Text(
                text = "テスト",
                //            color = Color.Gray,
                fontSize = 16.sp,
//                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    val navController = rememberNavController()
//    ComicManageTheme {
//        ComicDetail(navController)
//    }
//}
@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    ComicManageTheme {
//        ComicDetail("","")
    }
}