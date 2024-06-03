package com.example.furniture.ui.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.furniture.R
import com.example.furniture.ui.theme.AppTheme

data class ProfileItemFeatureProps(
    val name: String,
    val content: String,
    val onPress: () -> Unit
)

@Composable
fun ProfileItemFeature(item: ProfileItemFeatureProps, index: Int) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                       item.onPress()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = item.name, style = AppTheme.appTypography.text18Nunitosan)
            Text(
                text = item.content,
                style = AppTheme.appTypography.text12Nunitosan,
                modifier = Modifier.padding(top = 10.dp)
            )

        }
        Image(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Icon action $index",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileItemFeaturePreview() {
    AppTheme {
        val item = ProfileItemFeatureProps("My favorites", "ok la hellugulugu", {})
        ProfileItemFeature(item = item, index = 0)
    }
}