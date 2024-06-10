package com.example.furniture.ui.screens.my_orders.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.furniture.components.Line
import com.example.furniture.data.model.response.Invoice
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.utils.calculateTimeDifference

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InvoiceCard(item: Invoice, currentPager: String, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Order No: ${subInvoiceId(item.id)}")
                Text(text = calculateTimeDifference(item.dateExport))
            }
            Line()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "Total Amount: ${item.totalPrice}")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate(NavigationUtils.invoiceDetails + "/${item.id}")
                    },
                    shape = RoundedCornerShape(
                        topEnd = 10.dp,
                        topStart = 0.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text(text = "Detail")
                }
                Text(
                    text = currentPager,
                    style = TextStyle(
                        color = when (currentPager) {
                            "Delivery" -> Color(0xFF00B157)
                            "Processing" -> Color.Yellow
                            else -> Color.Red
                        }
                    ),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
    }
}

fun subInvoiceId(id: String): String {

    return if (id.isEmpty()) {
        "Unknown"
    } else if (id.length < 10) {
        id.substring(id.length - 5)
    } else if (id.length < 20) {
        id.substring(id.length - 5)
    } else if (id.length < 30) {
        id.substring(id.length - 5)

    } else {
        id
    }

}