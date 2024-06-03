package com.example.furniture.ui.screens.payment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.ImageBackground
import com.example.furniture.data.model.response.Payment
import com.example.furniture.ui.theme.AppTheme

@Composable
fun PaymentItem(item: Payment, index: Int, navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .wrapContentWidth()
                .padding(0.dp)) {
                Checkbox(
                    checked = item.isSelected,
                    onCheckedChange = {},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Black
                    )
                )
                Text(
                    text = "Use as default payment method",

                    )
            }
            Image(
                painter = painterResource(id = R.drawable.pen_icon),
                contentDescription = "Pen edit payment method",
                modifier = Modifier.size(20.dp)
            )
        }
       Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd){
           Image(painter = painterResource(id = R.drawable.background_bank), contentDescription = "Background bank")
           Image(painter = painterResource(id = R.drawable.wave), contentDescription = "Wave")
       }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentItemPreview() {
    AppTheme {
        PaymentItem(
            item = Payment(
                "1",
                "aa",
                "1017764509",
                "08/27",
                404,
                "VANH",
                true,
                "vietcombank",
                "Vietcombank",
                "img"
            ), index = 1, navController = rememberNavController()
        )
    }
}