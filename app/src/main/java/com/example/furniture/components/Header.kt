package com.example.furniture.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furniture.R
import com.example.furniture.ui.navigators.NavigationComponent
import com.example.furniture.ui.theme.AppTheme

@Composable
fun Header(
    iconLeft: Int?,
    iconRight: Int?,
    contentCenter: @Composable () -> Unit,
    sizeIconLeft: Dp?,
    sizeIconRight: Dp?,
    iconLeftPress: () -> Unit?,
    iconRightPress: () -> Unit?,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (iconLeft != null) {
            Image(
                modifier = Modifier
                    .size(sizeIconLeft ?: 30.dp)
                    .clickable {
                        iconLeftPress()
                    },
                painter = painterResource(id = iconLeft),
                contentDescription = "Icon left header"
            )

        } else {
            Image(
                modifier = Modifier
                    .size(sizeIconRight ?: 30.dp)
                    .clickable {

                    },
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "Icon left header",
                colorFilter = ColorFilter.tint(Color.Transparent)
            )
        }
        contentCenter()
        if (iconRight != null) {
            Image(
                modifier = Modifier
                    .size(sizeIconRight ?: 30.dp)
                    .clickable {
                        iconRightPress()
                    },
                painter = painterResource(id = iconRight),
                contentDescription = "Icon left header"
            )
        } else {
            Image(
                modifier = Modifier
                    .size(sizeIconRight ?: 30.dp)
                    .clickable {
                    },
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "Icon right header",
                colorFilter = ColorFilter.tint(Color.Transparent)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview() {
    val image: Int = R.drawable.background;
    AppTheme {
        Header(
            iconLeft = R.drawable.search_2,
            iconRight = R.drawable.cart,
            contentCenter = {
                Text(text = "Make")
                /*TODO*/
            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 27.dp,
            iconLeftPress = { /*TODO*/ },
            iconRightPress = {},
            modifier = Modifier
                .fillMaxWidth()

        )
    }
}