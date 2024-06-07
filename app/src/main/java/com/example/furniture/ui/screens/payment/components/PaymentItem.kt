package com.example.furniture.ui.screens.payment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.data.model.response.Payment
import com.example.furniture.data.viewmodel.PaymentViewModel
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils
import kotlinx.coroutines.launch

@Composable
fun PaymentItem(
    item: Payment,
    index: Int,
    navController: NavController,
    paymentViewModel: PaymentViewModel
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(

                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = item.isSelected,
                    onCheckedChange = {
                        paymentViewModel.viewModelScope.launch {
                          paymentViewModel.activePayment(item.id)

                        }

                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Black
                    )
                )
                Text(
                    text = "Use as default payment method",
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_bank),
                contentDescription = "Background bank",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                AsyncImage(
                    model = RetrofitUtils.BASE_URL + item.image,
                    contentDescription = "Icon bank",
                    modifier = Modifier
                        .size(25.dp)
                        .clip(RoundedCornerShape(10))
                )
                Text(
                    text = renderCardNumber("1017764509"), letterSpacing = 10.sp, style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White
                    ),

                    modifier = Modifier.padding(vertical = 10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Card Holder Name",
                            modifier = Modifier.padding(bottom = 10.dp),
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                        Text(
                            text = item.cartHolderName, style = TextStyle(
                                color = Color.White
                            )
                        )
                    }

                    Column {
                        Text(
                            text = "Expiry Date",
                            modifier = Modifier.padding(bottom = 10.dp),
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                        Text(
                            text = item.expiryDate, style = TextStyle(
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

fun renderCardNumber(cardNumber: String): String {
    var parseCardNumber = ""
    for ((i, item) in cardNumber.withIndex()) {
        if (i != 0 && i % 4 == 0) {
            parseCardNumber += " "
        }
        if (i < 8) {
            parseCardNumber += "*"
        } else {
            parseCardNumber += item
        }
    }
    return parseCardNumber
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentItemPreview() {
    AppTheme {

    }
}