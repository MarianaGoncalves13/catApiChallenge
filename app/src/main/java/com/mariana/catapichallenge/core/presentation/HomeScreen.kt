package com.mariana.catapichallenge.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mariana.catapichallenge.R
import com.mariana.catapichallenge.catlist.presentation.CatListUiEvent
import com.mariana.catapichallenge.catlist.presentation.CatListViewModel
import com.mariana.catapichallenge.catlist.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val catListViewModel = hiltViewModel<CatListViewModel>()
    val catState = catListViewModel.catListState.collectAsState().value
    val bottomNavController = rememberNavController()

    Scaffold(bottomBar = {
            BottomNavigationBar(
                bottomNavController = bottomNavController,
                onEvent = catListViewModel::onEvent)
        }, topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (catState.isCurrentListPage)
                            stringResource(R.string.cat_breeds)
                        else stringResource(R.string.my_favorite_cats),
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier.shadow(2.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screen.CatList.rout
            ) {
                composable(Screen.CatList.rout) {
                    CatListScreen(
                        navController = navController,
                        catListState = catState,
                        onEvent = catListViewModel::onEvent
                    )
                }
                composable(Screen.FavoriteCat.rout) {
                    //ListOfCats()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController,
    onEvent: (CatListUiEvent) -> Unit
) {
    val items = listOf(
        BottomItem(
            tittle = stringResource(R.string.home),
            icon = Icons.Rounded.Home
        ),
        BottomItem(
            tittle = stringResource(R.string.favorites),
            icon = Icons.Rounded.Favorite
        )
    )
    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selected.intValue == index,
                    onClick = {
                        selected.intValue = index
                        when (selected.intValue) {
                            0 -> {
                                onEvent(CatListUiEvent.Navigate)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.CatList.rout)
                            }

                            1 -> {
                                onEvent(CatListUiEvent.Navigate)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.FavoriteCat.rout)
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.tittle,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = bottomItem.tittle,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}

data class BottomItem(
    val tittle: String,
    val icon: ImageVector
)
