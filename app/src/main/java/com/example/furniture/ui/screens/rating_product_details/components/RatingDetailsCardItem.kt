package com.example.furniture.ui.screens.rating_product_details.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.model.response.ResponseRatingDetails
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils
import com.example.furniture.utils.calculateTimeDifference

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RatingDetailsCardItem(item: ResponseRatingDetails) {
    Box(modifier = Modifier.fillMaxWidth(), ) {
        val AVATAR_SIZE = 30

        Column(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .offset(y = (AVATAR_SIZE / 2).dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.name)
                Text(
                    text = calculateTimeDifference(item.time),
                    style = AppTheme.appTypography.textProduct
                )
            }
            Row(modifier = Modifier.padding(vertical = 7.dp)) {
                repeat(item.rate) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "star",
                        modifier = Modifier
                            .size(18.dp)
                            .padding(end = 4.dp)
                    )
                }
                if (item.rate < 5) {
                    repeat(5 - item.rate) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "star",
                            modifier = Modifier
                                .size(18.dp)
                                .padding(end = 4.dp),
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                    }
                }
            }
            if (item.comment.isNotEmpty()) {
                Text(
                    text = item.comment,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Justify,
                )
            }
        }
        if (item.avatar.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.avatar_test),
                contentDescription = "Avatar",
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(AVATAR_SIZE.dp)
                    .align(alignment = Alignment.TopCenter)
                ,
                contentScale = ContentScale.Fit
            )
        } else {
            AsyncImage(
                model = RetrofitUtils.BASE_URL + item.avatar, contentDescription = "Avatar",
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(AVATAR_SIZE.dp)
                    .align(alignment = Alignment.TopCenter)
                ,
                contentScale = ContentScale.Fit
            )
        }
    }
}