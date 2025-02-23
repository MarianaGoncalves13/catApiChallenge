package com.mariana.catapichallenge.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mariana.catapichallenge.catlist.presentation.CatListState
import com.mariana.catapichallenge.catlist.presentation.CatListUiEvent
import com.mariana.catapichallenge.catlist.presentation.components.CatItem

@Composable
fun CatListScreen(
    catListState: CatListState,
    navController: NavHostController,
    onEvent: (CatListUiEvent) -> Unit
) {
    if (catListState.catList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize())
        {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(catListState.catList.size) { index ->
                CatItem(
                    cat = catListState.catList[index],
                    navHostController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))
                if(index >= catListState.catList.size - 1 && !catListState.isLoading) {
                    onEvent(CatListUiEvent.Paginate(isFavorite = false))
                }
            }
        }
    }
}
