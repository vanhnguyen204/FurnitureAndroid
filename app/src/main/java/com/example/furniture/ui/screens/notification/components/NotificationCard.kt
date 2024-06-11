package com.example.furniture.ui.screens.notification.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furniture.components.Line
import com.example.furniture.data.model.response.NotificationResponse
import com.example.furniture.ui.theme.AppTheme

@Composable
fun NotificationCard(item: NotificationResponse, index: Int) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)){
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = "Image notification",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(modifier = Modifier.padding(start = 15.dp)) {
                Text(
                    text = item.title,
                    style = AppTheme.appTypography.titleHeaderStyle.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = item.message,
                    style = AppTheme.appTypography.textProduct
                )
            }
        }
        Line()
    }
}