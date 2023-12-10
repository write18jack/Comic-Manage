package com.whitebeach.comicmanage.presentation.applist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.whitebeach.comicmanage.app.component.CollapsingLayout
import com.whitebeach.comicmanage.app.theme.ComicManageTheme

@Composable
fun AppListScreen(navController: NavHostController) {
    CollapsingLayout(
        collapsingTop = {
            SearchBar()
        },
        bodyContent = {
            ComicAppList()
        },
        modifier = Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier

            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray, RoundedCornerShape(9.dp))
    ) {
        Image(

            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(40.dp)
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        )
    }
}

@Composable
fun ComicAppList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
    ) {
        items(7) {
            ComicAppListItem()
        }
    }
}

@Composable
fun ComicAppListItem(

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                Color.White,
                RoundedCornerShape(9.dp)
            )
    ) {
        Image(

            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(50.dp)
        )
        Column {
            Text(
                text = "App Name",
                fontSize = 30.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    ComicManageTheme {
        ComicAppList()
    }
}