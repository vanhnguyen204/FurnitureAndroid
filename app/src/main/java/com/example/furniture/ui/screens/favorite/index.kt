package com.example.furniture.ui.screens.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.furniture.R
import com.example.furniture.components.DialogConfirm
import com.example.furniture.components.Header
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.viewmodel.FavoriteViewModel
import com.example.furniture.helper.Console
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.ui.screens.favorite.components.ListFavorite
import com.example.furniture.ui.theme.AppTheme

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>()) {
    val favoriteProducts by favoriteViewModel.favoriteProducts.collectAsState()
    val isVisibleDialogConfirm by favoriteViewModel.isConfirmRemove.observeAsState()
    val context = LocalContext.current
    val getToken = SharedPreferencesHelper(context).getDataLocalStorage(Storage.TOKEN.toString(), "") ?: ""
    var productId by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            iconLeft = R.drawable.search_2,
            iconRight = R.drawable.cart,
            contentCenter = {
                Text(text = "Favorites")

            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 27.dp,
            iconLeftPress = {  },
            iconRightPress = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        ListFavorite(data = favoriteProducts, setProductId = {
            productId = it
        } )
       DialogConfirm(
           visible = isVisibleDialogConfirm == true,
           title = "Cảnh báo",
           message = "Bạn có chắc muốn xoá sản phẩm này khỏi danh sách yêu thích không?",
           onCancel = {
               favoriteViewModel.showDialogConfirm(false)
           },
           onConfirm = {
               favoriteViewModel.removeFavorite("Bear $getToken", RequestBodyFavorite(productId) )
              favoriteViewModel.getFavorites()
           })
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    AppTheme {
        FavoriteScreen()
    }
}
