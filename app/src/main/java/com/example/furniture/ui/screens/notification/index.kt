package com.example.furniture.ui.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.model.response.NotificationResponse
import com.example.furniture.ui.screens.notification.components.NotificationCard
import com.example.furniture.ui.theme.AppTheme

@Composable
fun NotificationScreen() {
    val notification1 = NotificationResponse(
        R.drawable.chair_single,
        "Your order #123456789 has been confirmed",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec."
    )
    val notification2 = NotificationResponse(
        R.drawable.lamp_product,
        "Your order #123456789 has been confirmed",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec."
    )
    val notification3 = NotificationResponse(
        R.drawable.chair_single,
        "Your order #123456789 has been confirmed",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec."
    )
    val notification4 = NotificationResponse(
        R.drawable.coffee_chair,
        "Your order #123456789 has been confirmed",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec."
    )
    val notification5 = NotificationResponse(
        R.drawable.chair_single,
        "Your order #123456789 has been confirmed",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec."
    )
    val notification6 = NotificationResponse(
        R.drawable.chair_colour,
        "Your order #123456789 has been confirmed",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec."
    )
    val notifications = listOf(
        notification1,
        notification2,
        notification3,
        notification4,
        notification5,
        notification6
    )
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Header(
            iconLeft = R.drawable.search_2,
            iconRight = null,
            contentCenter = {
                Text(text = "Notifications", style = AppTheme.appTypography.titleHeaderStyle)
            },
            sizeIconLeft = 30.dp,
            sizeIconRight = null,
            iconLeftPress = { },
            iconRightPress = { },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            item {
                NotificationCard(item = notification1, index = 100)
            }
            item {
                NotificationCard(item = notification1, index = 100)
            }
            item {
                Column(
                    modifier = Modifier

                        .background(Color(0xFFF2F2F2))
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Discover hot sale furnitures this week.",
                        style = AppTheme.appTypography.titleHeaderStyle
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec. ",
                        style = AppTheme.appTypography.textProduct
                    )
                    Text(
                        text = "HOT!",
                        style = AppTheme.appTypography.titleHeaderStyle.copy(
                            fontSize = 14.sp,
                            color = Color(0xFFEB5757)
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
            itemsIndexed(notifications) { index, item ->
                NotificationCard(item = item, index = index)
            }
        }
    }
}