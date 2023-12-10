package com.whitebeach.comicmanage.presentation.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScrollableTabRow(
    tabIndex: Int,
    operation: (Int) -> Unit,
    titles: List<String>,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
){
//    val tabWidth = remember {
//        val tabWidthStateList = mutableStateListOf<Dp>()
//        repeat(titles.size){
//            tabWidthStateList.add(0.dp)
//        }
//        tabWidthStateList
//    }

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