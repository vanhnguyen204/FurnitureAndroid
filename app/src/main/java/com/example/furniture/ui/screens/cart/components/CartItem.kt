package com.example.furniture.ui.screens.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.model.response.Cart
import com.example.furniture.ui.screens.product_details.UpAndDown
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils

@Composable
fun CartItem(item: Cart) {
    var quantity by remember {
        mutableIntStateOf(item.quantity)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        val (imageLeft, layoutRight, line) = createRefs()
        AsyncImage(
            model = RetrofitUtils.BASE_URL + item.image,
            contentDescription = "Image product",
            modifier = Modifier
                .constrainAs(imageLeft) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(layoutRight) {
                    start.linkTo(imageLeft.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(line.top)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.name, style = AppTheme.appTypography.textProduct)
                    Image(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Remove out of cart",
                        modifier = Modifier.size(25.dp)
                    )
                }
                Text(text = "$ ${item.price}", style = AppTheme.appTypography.priceProduct)

            }
            UpAndDown(value = quantity, onUpPress = { quantity = ++quantity }) {
                if (quantity > 1) {
                    --quantity
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(AppTheme.appColors.appGray)
                .constrainAs(line) {
                    top.linkTo(imageLeft.bottom)
                })
    }
}