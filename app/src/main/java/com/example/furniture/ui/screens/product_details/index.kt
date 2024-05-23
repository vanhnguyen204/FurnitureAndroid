package com.example.furniture.ui.screens.product_details

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.furniture.R
import com.example.furniture.components.DialogConfirm
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.request.RequestBodyFavorite
import com.example.furniture.data.model.response.Product
import com.example.furniture.data.viewmodel.FavoriteViewModel
import com.example.furniture.data.viewmodel.ProductViewModel
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.ui.screens.product_details.components.LeftBarSelector
import com.example.furniture.ui.screens.product_details.components.ViewPager
import com.example.furniture.ui.screens.product_details.components.ViewPagerDotsIndicator
import com.example.furniture.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetails(
    navHostController: NavHostController,
    idProductItem: String?,
    favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>(),
    productViewModel: ProductViewModel = hiltViewModel<ProductViewModel>()
) {
    val context = LocalContext.current
    val isVisibleDialogConfirm by favoriteViewModel.isConfirmRemove.observeAsState()
    val productDetails by productViewModel.productDetails.observeAsState()
    val isProductFavorite by favoriteViewModel.isProductFavorite.observeAsState()
    val getToken =
        SharedPreferencesHelper(context).getDataLocalStorage(Storage.TOKEN.toString(), "") ?: ""
    LaunchedEffect(key1 = idProductItem) {

        favoriteViewModel.checkIsLike(
            "Bear $getToken",
            productId = RequestBodyFavorite(idProductItem!!)
        )
        productViewModel.productDetails(
            "Bear $getToken",
            productId = RequestBodyFavorite(idProductItem)
        )
    }


    var value by remember {
        mutableIntStateOf(1)
    }
    val coroutineScope = rememberCoroutineScope()
    val pageCount = 3
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Box(
                modifier = Modifier
                    .height(455.dp)
                    .width(349.dp)
            ) {
                ViewPager(state = pagerState, modifier = Modifier.matchParentSize())
                ViewPagerDotsIndicator(
                    Modifier
                        .height(50.dp)
                        .align(Alignment.BottomEnd)
                        .padding(end = 50.dp),
                    pageCount = pageCount,
                    currentPageIteration = pagerState.currentPage
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .offset(x = (-25).dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .padding(5.dp)
                            .clickable {
                                navHostController.popBackStack()
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.left),
                            contentDescription = "Back image",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.White)
                            .padding(10.dp)
                    ) {
                        LeftBarSelector(currentPageIteration = pagerState.currentPage, onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(it)
                            }

                        })
                    }
                }
            }
        }
        Column(modifier = Modifier.padding(20.dp)) {
            val product: Product? = productDetails?.data
            if (product != null) {
                Text(
                    text = product.name, style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(AppTheme.appFonts.gelasioRegular),
                        fontWeight = FontWeight(500),
                        lineHeight = TextUnit(30f, TextUnitType(30L))
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$ ${product.price}", style = AppTheme.appTypography.h1.copy(
                            fontFamily = FontFamily(AppTheme.appFonts.nunitoSanMedium),
                            fontWeight = FontWeight(700)

                        )
                    )
                    UpAndDown(value = value, onUpPress = {
                        value = ++value
                    }, onDownPress = {
                        if (value > 1) {
                            value = --value
                        }
                    })
                }
                Row(
                    modifier = Modifier.padding(vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Star",
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "4.5", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(700)
                        ),
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )

                    Text(
                        text = "(50 reviews)",
                        style = AppTheme.appTypography.textProduct.copy(fontWeight = FontWeight(600))
                    )
                }
                Text(
                    text = product.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.appTypography.textProduct
                )
            }

        }
        FooterDetails(
            isFavorite = isProductFavorite == true,
            onMarkPress = {
                          if (isProductFavorite == true){
                              favoriteViewModel.showDialogConfirm(true)
                          }else{
                              favoriteViewModel.createFavorite("Bear $getToken", RequestBodyFavorite(idProductItem!!))
                          }
            },
            onAddToCartPress = {})

        DialogConfirm(
            visible = isVisibleDialogConfirm == true,
            title = "Cảnh báo",
            message = "Bạn có chắc muốn xoá sản phẩm này khỏi danh sách yêu thích không?",
            onCancel = {
                favoriteViewModel.showDialogConfirm(false)
            },
            onConfirm = {
                favoriteViewModel.removeFavorite("Bear $getToken", RequestBodyFavorite(idProductItem!!) )
                favoriteViewModel.toggleStatusFavorite()
            })
    }

}

@Composable
fun FooterDetails(isFavorite: Boolean, onMarkPress: () -> Unit, onAddToCartPress: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = 0.dp, y = 0.dp)
            .padding(horizontal = 10.dp)
    ) {
        val color = if (isFavorite) Color.Cyan else Color.White
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color)
                .padding(10.dp)
                .clickable {
                    onMarkPress()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.bookmark),
                contentDescription = "Book mark",
                modifier = Modifier.size(30.dp)

            )
        }
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
                .weight(1f)
                .clickable {
                    onAddToCartPress()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Add to cart", style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}

@Composable
fun UpAndDown(value: Int, onUpPress: () -> Unit, onDownPress: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(AppTheme.appColors.appGray)
                .padding(5.dp)
                .clickable {
                    onUpPress()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus_child),
                contentDescription = "Plus",
                modifier = Modifier.size(20.dp)
            )


        }
        Text(
            text = value.toString(), style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(AppTheme.appFonts.nunitoSanRegular)
            ), modifier = Modifier.padding(horizontal = 20.dp)
        )
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(AppTheme.appColors.appGray)
                .padding(5.dp)
                .clickable {
                    onDownPress()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Minus",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun UpAndDownPreview() {
    AppTheme {
//        var value by remember {
//            mutableIntStateOf(1)
//        }
//        UpAndDown(value = value, onUpPress = {
//            value = ++value
//        }, onDownPress = {
//            if (value > 1) {
//                value = --value
//            }
//        })
        FooterDetails(isFavorite = true, onMarkPress = {}, onAddToCartPress = {})
    }
}
