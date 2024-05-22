package com.example.furniture.ui.screens.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.model.Product
import com.example.furniture.data.viewmodel.FavoriteViewModel
import com.example.furniture.helper.Console
import com.example.furniture.ui.screens.favorite.components.ListFavorite
import com.example.furniture.ui.theme.AppTheme

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>()) {
    val favoriteProducts by favoriteViewModel.favoriteProducts.collectAsState()
    Console().log("FAVORITE", favoriteProducts.size.toString())
    Column {
        Header(
            iconLeft = R.drawable.search_2,
            iconRight = R.drawable.cart,
            contentCenter = {
                Text(text = "Favorites")

            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 27.dp,
            iconLeftPress = {  },
            iconRightPress = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        ListFavorite(data = favoriteProducts)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    AppTheme {
        FavoriteScreen()
    }
}
