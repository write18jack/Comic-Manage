package com.whitebeach.comicmanage

import android.graphics.drawable.Icon
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var tabIndex by remember { mutableIntStateOf(0) }
    val titles = listOf("Tab 0", "Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { titles.size }

    Scaffold(modifier = modifier) { it ->
        Column(
            modifier = Modifier
                .padding(it)
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
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableTabRowSample(
    tabIndex: Int,
    operation: (Int) -> Unit,
    titles: List<String>,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
){
    val tabWidth = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(titles.size){
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    ScrollableTabRow(
        selectedTabIndex = tabIndex,
        modifier = Modifier.fillMaxWidth(),
        contentColor = Color.Cyan,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[tabIndex],
                    //tabWidth = tabWidth[tabIndex]
                )
            )
        }
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = tabIndex == index,
                onClick = {
                    coroutineScope.launch {
                        operation(index)
                        pagerState.animateScrollToPage(index)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                text = {
                    Text(text = "Tab $title")
                }
            )
        }
    }
}
val comicList = listOf("one piece", "laliga", "metropolitano")

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerSample(
    modifier: Modifier = Modifier,
    pageCount: Int,
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
    ) {page ->
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting()
}