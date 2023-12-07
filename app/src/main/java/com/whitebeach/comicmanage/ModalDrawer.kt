package com.whitebeach.comicmanage

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitebeach.comicmanage.ui.theme.ComicManageTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun ModalDrawer(){
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ComicManageTheme {
        Surface{
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
                Box {
                    Text(text = "Atletico", fontSize = 30.sp)
                }
            }
        }
    }
}

data class AppDrawerItemInfo<T>(
    val drawerOption: T,
    @StringRes val title: Int,
    val iconId: ImageVector,
    @StringRes val descriptionId: Int
)

enum class MainNavOption {
    HomeScreen,
    AboutScreen,
    SettingsScreen,
}

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            MainNavOption.HomeScreen,
            title = com.whitebeach.comicmanage.R.string.drawer_home,
            iconId = Icons.Filled.Home,
            descriptionId = com.whitebeach.comicmanage.R.string.drawer_home_description
        ),
        AppDrawerItemInfo(
            MainNavOption.SettingsScreen,
            com.whitebeach.comicmanage.R.string.drawer_settings,
            iconId = Icons.Filled.AddCircle,
            com.whitebeach.comicmanage.R.string.drawer_settings_description
        ),
        AppDrawerItemInfo(
            MainNavOption.AboutScreen,
            com.whitebeach.comicmanage.R.string.drawer_about,
            iconId = Icons.Filled.AddCircle,
            com.whitebeach.comicmanage.R.string.drawer_info_description
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AppDrawerItem(item: AppDrawerItemInfo<T>, onClick: (options: T) -> Unit) =
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.width(200.dp).padding(16.dp),
        onClick = { onClick(item.drawerOption) },
        shape = RoundedCornerShape(50),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                imageVector = item.iconId,
                contentDescription = stringResource(id = item.descriptionId),
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = item.title),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>> AppDrawerContent(
    drawerState: DrawerState,
    menuItems: List<AppDrawerItemInfo<T>>,
    defaultPick: T,
    onClick: (T) -> Unit
) {
    var currentPick by remember { mutableStateOf(defaultPick) }
    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(menuItems) { item ->
                        AppDrawerItem(item = item) { navOption ->

                            if (currentPick == navOption) {
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                return@AppDrawerItem
                            }

                            currentPick = navOption
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            onClick(navOption)
                        }
                    }
                }
            }
        }
    }
}