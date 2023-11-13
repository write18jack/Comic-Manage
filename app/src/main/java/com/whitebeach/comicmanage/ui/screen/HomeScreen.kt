package com.whitebeach.comicmanage.ui.screen

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.whitebeach.comicmanage.ui.CollapsingLayout
import com.whitebeach.comicmanage.R
import com.whitebeach.comicmanage.ui.MainContent
import com.whitebeach.comicmanage.ui.theme.ComicManageTheme

@Composable
fun HomeScreen(navController: NavHostController) {
    CollapsingLayout(
        collapsingTop = {
            AppIcons()
        },
        bodyContent = {
            ReadingList()
        },
        modifier = Modifier
    )
}


@Composable
fun AppIcons(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier
    ) {
        items(10) {
            AppIconItem()
        }
    }
}

@Composable
fun AppIconItem(

) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .size(80.dp)
            .clip(shape = RoundedCornerShape(size = 70f)),
            //.border(4.dp, color = Color.Black),
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ReadingList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
    ) {
        items(30) {
            ReadingItem()
        }
    }
}

@Composable
fun ReadingItem(

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(5.dp))
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
            Text(
                text = "details",
                fontSize = 15.sp
            )
        }
    }
}

//@Preview
//@Composable
//fun LazyPreview(){
//    AppIcons()
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComicManageTheme {
       MainContent()
    }
}