package com.example.furniture.ui.screens.checkout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furniture.R
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils

data class DeliveryOptions(
    val icon: Int,
    val type: String,
    val name: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryMethod(
    onValueChange: (item: DeliveryOptions) -> Unit
) {
    val fastDelivery = DeliveryOptions(R.drawable.fast_delivery, "fast", "Fast (2 - 3 days)")
    val normalDelivery = DeliveryOptions(R.drawable.fast_delivery, "normal", "Normal (4 - 5 days)")
    val deliveryOptions = listOf(fastDelivery, normalDelivery)
    var expanded by remember { mutableStateOf(false) }

    var selected by remember {
        mutableStateOf(deliveryOptions[0])
    }
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Delivery method",
                style = AppTheme.appTypography.textProduct.copy(fontSize = 18.sp)
            )
            Image(
                painter = painterResource(id = R.drawable.pen_icon),
                contentDescription = "Icon pen",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {

                        expanded = true
                    }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
                .background(color = Color.White),
        ) {

            Row(
                modifier = Modifier.padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = selected.icon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = selected.name)
            }

            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .shadow(8.dp)


            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    deliveryOptions.forEachIndexed { index, item ->
                        DropdownMenuItem(text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = item.name)
                            }
                        }, onClick = {
                            selected = item
                            expanded = false
                            onValueChange(item)
                        })

                    }
                }
            }
        }
    }
}