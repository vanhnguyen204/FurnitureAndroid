package com.example.furniture.ui.screens.product_details.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.viewmodel.FavoriteViewModel
import com.example.furniture.data.viewmodel.ProductViewModel
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(
    state: PagerState,
    modifier: Modifier,
   productViewModel: ProductViewModel = hiltViewModel<ProductViewModel>()

) {
    val productDetails by productViewModel.productDetails.observeAsState()
    HorizontalPager(
        state = state,
        modifier = modifier
    ) {
        AsyncImage(
            model = RetrofitUtils.BASE_URL + "" + productDetails?.data?.image,
            contentDescription = "Image pager",
            modifier = Modifier
                .clip(
                    RoundedCornerShape(bottomStart = 50.dp)
                )
                .fillMaxSize()
                ,
            contentScale = ContentScale.FillBounds,
        )


    }

}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewPagerPreview() {
    AppTheme {
        val pageCount = 3
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })
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
            }
        }
    }
}