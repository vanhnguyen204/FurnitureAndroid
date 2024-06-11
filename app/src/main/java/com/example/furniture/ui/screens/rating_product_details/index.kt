package com.example.furniture.ui.screens.rating_product_details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.viewmodel.NavArgsRatingDetail
import com.example.furniture.data.viewmodel.RatingViewModel
import com.example.furniture.data.viewmodel.ShareDataBetweenScreenViewModel
import com.example.furniture.ui.screens.rating_product_details.components.RatingDetailsCardItem
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.utils.RetrofitUtils
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RatingProductDetails(

    navController: NavController,
    productId: String,
    imageProduct: String,
    name: String,
    ratingViewModel: RatingViewModel = hiltViewModel(),
) {
    val overallRating by ratingViewModel.overallRatingProduct.collectAsState()
    val ratingAnReviews by ratingViewModel.ratingAnReviews.collectAsState()
    LaunchedEffect(productId) {
        ratingViewModel.getRatingAndReviewsDetails(productId)
        ratingViewModel.getOverallRating(productId)
    }
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF2F2F2)).padding(10.dp)) {
        Header(
            iconLeft = R.drawable.left,
            iconRight = null,
            contentCenter = {
                Text(text = "Invoice details", style = AppTheme.appTypography.titleHeaderStyle)
            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 30.dp,
            iconLeftPress = {
                navController.popBackStack()
                Unit
            },
            iconRightPress = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 10.dp)) {
            AsyncImage(
                model = RetrofitUtils.BASE_URL + imageProduct,
                contentDescription = "Image product",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillBounds,

                )
            Column(modifier = Modifier.fillMaxWidth().padding(start = 20.dp)) {
                Text(text = renderName(name), style = TextStyle(color = Color.Black))
                Row(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Star",
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = overallRating.averageRating.toString(), style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(700)
                        ),
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )

                    Text(
                        text = "(${overallRating.reviews} reviews)",
                        style = AppTheme.appTypography.textProduct.copy(fontWeight = FontWeight(600))
                    )
                }
            }
        }
        LazyColumn {
            items(ratingAnReviews) {
                RatingDetailsCardItem(item = it)
            }
        }
    }
}

fun renderName(name: String): String {
    var formatName = ""
    for ((index, item) in name.withIndex()) {
        if (item == '+') {
            formatName += " "
        } else {
            formatName += item
        }
    }
    return formatName
}