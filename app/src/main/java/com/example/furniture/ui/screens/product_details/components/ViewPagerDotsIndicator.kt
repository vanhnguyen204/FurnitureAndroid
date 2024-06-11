package com.example.furniture.ui.screens.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.furniture.ui.theme.AppTheme

@Composable
fun ViewPagerDotsIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPageIteration: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPageIteration == iteration) Color.Black else Color.White
            Box(
                modifier = Modifier
                    .width(if (currentPageIteration == iteration) 50.dp else 20.dp)
                    .padding(3.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color)
                    .height(5.dp)


            )
        }
    }
}

@Preview
@Composable
private fun ViewPagerDotsIndicatorPreview() {
    ViewPagerDotsIndicator(pageCount = 5, currentPageIteration = 2)
}