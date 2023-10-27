package com.example.pokedetails.ui.pokemon_detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.pokedetails.R
import com.example.pokedetails.data.pokemon.models.domain.Pokemon
import com.example.pokedetails.ui.pokemon_detail.shape.rememberTopConcaveCurve
import com.example.pokedetails.ui.theme.spacing

@Composable
fun PokemonInfo(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
) {
    Column(
        modifier = modifier
            .padding(MaterialTheme.spacing.dimen8)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(
                MaterialTheme.colorScheme.background,
                rememberTopConcaveCurve(topCurvature = MaterialTheme.spacing.dimen48)
            )
            .padding(top = MaterialTheme.spacing.dimen48)
    ) {
        PokemonAboutAndTypes(
            modifier = Modifier.fillMaxWidth(), pokemon = pokemon
        )
        PokemonMoves(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.dimen16),
            pokemon = pokemon
        )

    }
}

@Composable
fun PokemonAboutAndTypes(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        AboutColumn(
            modifier = Modifier.weight(1f), pokemon = pokemon
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(MaterialTheme.spacing.dimen1)
                .background(MaterialTheme.colorScheme.surfaceVariant),
        )
        PokemonTypesColumn(
            modifier = Modifier.weight(1f), pokemon = pokemon
        )

    }
}

@Composable
fun PokemonTypesColumn(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Types",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.dimen8))
        pokemon.pokemonType.forEach {
            PokemonType(type = it)
        }
    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonMoves(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.dimen12),
            text = "Moves",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        FlowRow(horizontalArrangement = Arrangement.SpaceEvenly) {
            pokemon.moves.forEach { move ->
                Text(
                    text = move,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.dimen4)
                        .border(
                            MaterialTheme.spacing.dimen1,
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.shapes.small
                        )
                        .padding(MaterialTheme.spacing.dimen4)
                )
            }
        }
    }
}

@Composable
fun AboutColumn(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "About",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        IconValueRow(
            modifier = Modifier.padding(top = MaterialTheme.spacing.dimen8),
            icon = painterResource(id = R.drawable.ic_height),
            value = "${pokemon.heightInMetre} m"
        )
        IconValueRow(
            icon = painterResource(id = R.drawable.ic_weight),
            value = "${pokemon.weightInGrams} g"
        )


    }

}

@Composable
private fun IconValueRow(
    modifier: Modifier = Modifier,
    icon: Painter,
    value: String
) {
    Row(
        modifier = modifier.padding(vertical = MaterialTheme.spacing.dimen4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = icon, contentDescription = null)
        Text(modifier = Modifier.padding(MaterialTheme.spacing.dimen8), text = value)
    }
}


@Composable
fun PokemonType(
    modifier: Modifier = Modifier,
    type: String
) {
    val typeColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.secondaryContainer
    )
    val backgroundColor = remember(type) {
        typeColors.random()
    }
    Text(
        modifier = modifier
            .padding(MaterialTheme.spacing.dimen4)
            .clip(MaterialTheme.shapes.small)
            .background(
                backgroundColor
            )
            .padding(
                horizontal = MaterialTheme.spacing.dimen8,
                vertical = MaterialTheme.spacing.dimen4
            ),
        text = type,
        color = contentColorFor(backgroundColor),
    )

}


