package com.whitebeach.comicmanage.presentation.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val comicList = listOf("one piece", "laliga", "metropolitano")

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeHorizontalPager(
    modifier: Modifier = Modifier,
    state: PagerState,
    navController: NavController
) {
    // Our page content
    HorizontalPager(
        modifier = modifier,
        state = state,
        pageSpacing = 0.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(7.dp),
        beyondBoundsPageCount = 0,
        pageSize = PageSize.Fill,
        flingBehavior = PagerDefaults.flingBehavior(state = state),
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        )
    ) { page ->
        // Our page content
        Box(Modifier.fillMaxSize()) {
            DayOfWeekList(
                page = page,
                comics = comicList,
                navController = navController
            )
        }
    }
}

@Composable
fun DayOfWeekList(
    page: Int,
    comics: List<String>,
    navController: NavController
) {
    Column {
        Text(text = "$page")
        LazyColumn(
            modifier = Modifier.background(Color.LightGray),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(5.dp)
        ) {
            itemsIndexed(comics) { index, comic ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(5.dp))
                        .clickable {
                            navController.navigate(route = "SampleDetailScreen/$index")
                        }
                ) {
                    Text(
                        text = " $comic ",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Composable
fun SampleDetailScreen(
    itemIndex: Int?,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
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

        Text(
            text = "$itemIndex",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 40.sp
        )
    }
}