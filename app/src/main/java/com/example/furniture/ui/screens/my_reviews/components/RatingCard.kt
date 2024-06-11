package com.example.furniture.ui.screens.my_reviews.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.model.response.RatingReview
import com.example.furniture.ui.screens.payment_management.renderExpiryDate
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils
import com.example.furniture.utils.calculateTimeDifference

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RatingCard(item: RatingReview) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(15.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = RetrofitUtils.BASE_URL + item.image,
                contentDescription = "Image product",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds
            )
            Column {
                Text(text = item.name, style = AppTheme.appTypography.textProduct)
                Text(text = "$ ${item.price}", style = AppTheme.appTypography.priceProduct)
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row (modifier = Modifier.padding(vertical = 15.dp)){
                repeat(item.rate) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "star",
                        modifier = Modifier.size(20.dp)
                    )
                }
                if (item.rate < 5) {
                    repeat(5 - item.rate) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "star",
                            modifier = Modifier.size(20.dp),
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                    }
                }
            }
            Text(text = calculateTimeDifference(item.time))
        }
        Text(text = item.comment, textAlign = TextAlign.Justify)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun RatingCardPreview() {
    AppTheme {
        RatingCard(
            item = RatingReview(
                "",
                "Chair",
                20,
                "",
                "",
                "",
                "",
                3,
                "Nice Furniture with good delivery. The delivery time is very fast. Then products look like exactly the picture in the app. Besides, color is also the same and quality is very good despite very cheap price",
                "2024-06-10 11:00:00"
            )
        )
    }
}