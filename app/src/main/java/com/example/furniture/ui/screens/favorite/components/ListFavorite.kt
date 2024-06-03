package com.example.furniture.ui.screens.favorite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.model.response.Product
import com.example.furniture.data.viewmodel.FavoriteViewModel
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils
import kotlinx.coroutines.launch

@Composable
fun ListFavorite(data: List<Product>, setProductId: (productId: String) -> Unit) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),

        ) {
        itemsIndexed(data) { index: Int, item: Product ->
            FavoriteItem(index = index, item = item, setProductId = {
                setProductId(it)
            })
        }
    }
}

@Composable
fun FavoriteItem(
    item: Product,
    index: Int,
    setProductId: (productId: String) -> Unit,
    favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>()
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        val (imageLeft, containerRight, line) = createRefs()
        AsyncImage(
            model = RetrofitUtils.BASE_URL + "" + item.image,
            contentDescription = "Product image $index",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .height(100.dp)
                .width(100.dp)
                .constrainAs(imageLeft) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            contentScale = ContentScale.FillBounds,
        )
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(containerRight) {
                    start.linkTo(imageLeft.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints

                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Text(text = item.name, style = AppTheme.appTypography.textProduct)
                Text(text = "$ ${item.price}", style = AppTheme.appTypography.priceProduct)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close_2),
                    contentDescription = "Delete favorite $index",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            setProductId(item.id)
                            favoriteViewModel.viewModelScope.launch {
                                favoriteViewModel.showDialogConfirm(true)
                            }
                        }
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(AppTheme.appColors.appGray)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shopping_bag),
                        contentDescription = "Delete favorite $index",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(AppTheme.appColors.appGray)
                .constrainAs(line) {
                    top.linkTo(imageLeft.bottom)
                }) {}
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    val product: Product =
        Product("1", "Chair ", 30, "/images/chair-product.png", "Hello ae", "chair", "admin_test")
    val products = listOf(product, product, product)
    AppTheme {

    }
}