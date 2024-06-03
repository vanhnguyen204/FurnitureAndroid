package com.example.furniture.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.furniture.ui.theme.AppTheme

@Composable
fun FormAddress(title: String, isVisible: Boolean, onClose: () -> Unit) {
    val (country, setCountry) = remember {
        mutableStateOf("")
    }
    if (isVisible) {
        Dialog(onDismissRequest = onClose) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {


                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color(0xFF937350))
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = title, style = AppTheme.appTypography.largeTitle)
                    OutlinedTextField(value = country, onValueChange = {

                    }, modifier = Modifier.padding(top = 10.dp),  placeholder = {
                        Text(text = "Country")
                    })
                    OutlinedTextField(value = country, onValueChange = {

                    }, modifier = Modifier.padding(top = 10.dp),  placeholder = {
                        Text(text = "City")
                    })

                    OutlinedTextField(value = country, onValueChange = {

                    },  modifier = Modifier.padding(top = 10.dp), placeholder = {
                        Text(text = "District")
                    })
                    OutlinedTextField(value = country, onValueChange = {

                    }, modifier = Modifier.padding(top = 10.dp),  placeholder = {
                        Text(text = "Address detail")
                    } )
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormAddressPreview() {
    AppTheme {
        FormAddress(isVisible = true, onClose = {}, title = "New Shipping Address")
    }
}

