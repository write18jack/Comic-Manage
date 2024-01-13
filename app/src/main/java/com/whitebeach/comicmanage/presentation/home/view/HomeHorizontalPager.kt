package com.whitebeach.comicmanage.presentation.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.whitebeach.comicmanage.presentation.applist.ComicAppListItem


data class Manga(
    val title: String,
    val author: String,
    val tags: List<String>
)

val mangaList = listOf(
    Manga("One Piece", "Eiichiro Oda", listOf("Monday", "Adventure", "Comedy", "Fantasy")),
    Manga("Naruto", "Masashi Kishimoto", listOf("Tuesday", "Action", "Adventure", "Martial Arts")),
    Manga("Attack on Titan", "Hajime Isayama", listOf("Wednesday", "Action", "Drama", "Horror"))
)

// 掲掲載曜日のタグをつける関数
fun addTag(manga: Manga): Manga {
    return manga.copy(tags = manga.tags + listOf("Weekly"))
}

// フィルターする関数
fun filterByTag(tag: String): List<Manga> {
    return mangaList.filter { it.tags.contains(tag) }
}
// Mondayのタグがついているリストをフィルターする関数
fun filterByMonday(): List<Manga> {
    return mangaList.filter { it.tags.contains("Monday") }
}
// フィルターしたリストからタイトルだけを取り出す関数
fun getTitle(mangaList: List<Manga>): List<String> {
    return mangaList.map { it.title }
}

val comicList = listOf(
    "one piece",
    "laliga",
    "metropolitano",
    "wed",
    "mon"
    )

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
                comics = getTitle(filterByTag("Monday")),
                navController = navController,
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

@OptIn
@Composable
fun SampleDetailScreen(
    itemIndex: Int?,
    navController: NavController,
    modifier: Modifier = Modifier
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
        ComicDetailItem()
//        LazyColumn(
//            modifier
//        ) {
//            items(2) {
//                ComicDetailItem()
//            }
//        }
    }
}

@Composable
fun ComicDetailItem(
) {
    Column(
        modifier = Modifier
            .padding(40.dp),

        ) {
        Box(modifier = Modifier
//            .padding(horizontal = 130.dp)
            .size(180.dp)
            .align(Alignment.CenterHorizontally)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            )){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                ) {
                Image(

                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.End)
                )
            }
        }

        Text(
            text = "タイトル",
            fontSize = 16.sp,
        )
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            )
        Text(
            text = "著者",
            fontSize = 16.sp,
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        )
        Text(
            text = "アプリ名",
            fontSize = 16.sp,
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        )
        Text(
            text = "ジャンル",
            fontSize = 16.sp,
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        )
        Text(
            text = "タグ",
            fontSize = 16.sp,
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        )
    }
}

//@Preview
//@Composable
//fun PreviewGreeting() {
//    SampleDetailScreen()
//}