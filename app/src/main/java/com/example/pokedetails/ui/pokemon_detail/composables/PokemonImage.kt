package com.example.pokedetails.ui.pokemon_detail.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import kotlinx.coroutines.launch

@Composable
fun PokemonImage(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    val images = remember(pokemon) {
        pokemon.image.getImageList()
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.Center
    ) {
        if (images.isEmpty()) {
            Text(text = "No Images Found")
        } else {
            Images(images = images)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Images(
    modifier: Modifier = Modifier,
    images: List<String>
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {
        val pagerState = rememberPagerState {
            images.size
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight()
        ) { index ->
            AsyncImage(
                model = images[index],
                contentDescription = "Pokemon Image",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillHeight
            )
        }
        if (pagerState.canScrollBackward) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Image",
                )
            }
        }
        if (pagerState.canScrollForward) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Previous Image",
                )
            }
        }
    }
}
