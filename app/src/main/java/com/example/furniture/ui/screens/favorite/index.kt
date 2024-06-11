package com.example.furniture.ui.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.furniture.R
import com.example.furniture.components.DialogConfirm
import com.example.furniture.components.DialogMessage
import com.example.furniture.components.Header
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.viewmodel.CartViewModel
import com.example.furniture.data.viewmodel.FavoriteViewModel
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.ui.screens.favorite.components.ListFavorite
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import kotlinx.coroutines.launch


@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>(),
    cartViewModel: CartViewModel = hiltViewModel<CartViewModel>()
) {
    val favoriteProducts by favoriteViewModel.favoriteProducts.collectAsState()
    val isVisibleDialogConfirm by favoriteViewModel.isConfirmRemove.observeAsState()
    val context = LocalContext.current
    val getToken =
        SharedPreferencesHelper(context).getDataLocalStorage(Storage.TOKEN.toString(), "") ?: ""
    var productId by remember {
        mutableStateOf("")
    }
    var isSuccess by remember {
        mutableStateOf(false)
    }
    var messageAddAllSuccessOrFailed by remember {

        mutableStateOf(
            ""
        )
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

        Column {
            Header(
                iconLeft = R.drawable.search_2,
                iconRight = R.drawable.cart,
                contentCenter = {
                    Text(text = "Favorites",style = AppTheme.appTypography.titleHeaderStyle)

                },
                sizeIconLeft = 30.dp,
                sizeIconRight = 27.dp,
                iconLeftPress = { },
                iconRightPress = {
                    navController.navigate(NavigationUtils.cart)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )


            if (favoriteProducts.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "You haven't liked any products yet.", textAlign = TextAlign.Center)
                }
                return
            }

            ListFavorite(data = favoriteProducts, setProductId = {
                productId = it
            }, cartViewModel, navController = navController)

            DialogConfirm(
                visible = isVisibleDialogConfirm == true,
                title = "Cảnh báo",
                message = "Bạn có chắc muốn xoá sản phẩm này khỏi danh sách yêu thích không?",
                onCancel = {
                    favoriteViewModel.showDialogConfirm(false)
                },
                onConfirm = {
                    favoriteViewModel.removeFavorite(
                        "Bear $getToken",
                        RequestBodyFavorite(productId)
                    )
                    favoriteViewModel.getFavorites()
                })
        }
        DialogMessage(
            visibility = isSuccess,
            onClose = {
                isSuccess = false
            },
            title = "Notification",
            message = messageAddAllSuccessOrFailed,
            titleColor = Color.Black,
            messageColor = Color.Black
        )
        Button(
            shape = RoundedCornerShape(10),
            onClick = {
                val filterProductIds: List<String> = favoriteProducts.map {
                    it.id
                }
                cartViewModel.viewModelScope.launch {
                    val res = cartViewModel.addAllToCart(filterProductIds)
                    isSuccess = true
                    messageAddAllSuccessOrFailed = if (res) {

                        "Add all product to cart successfully!"
                    } else {
                        "Add all product to cart failed!"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),

            ) {
            Text(
                text = "Add all to cart", textAlign = TextAlign.Center, style = TextStyle(
                    color = Color.White
                ),
                modifier = Modifier.padding(10.dp)
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    AppTheme {

    }
}
