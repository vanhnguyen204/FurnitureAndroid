package com.example.furniture.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils

@Composable
fun DonePurchase(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "SUCCESS!", style = AppTheme.appTypography.largeTitle)
        Box {
            Image(
                painter = painterResource(id = R.drawable.done_background),
                modifier = Modifier
                    .size(260.dp)
                    .align(Alignment.Center),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFFF1EEEE))
            )
            Image(
                painter = painterResource(id = R.drawable.done_image),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center)
            )
            Image(
                painter = painterResource(id = R.drawable.done_icon),
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomCenter),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Your order will be delivered soon.\n" +
                    "Thank you for choosing our app!"
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                      navController.navigate(NavigationUtils.homeScreen){
                          popUpTo(navController.graph.startDestinationId) {
                              inclusive = true
                          }
                      }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        ) {
            Text(
                text = "BACK TO HOME", style = TextStyle(
                    color = Color.White
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DonePurchasePreview() {
    AppTheme {
        DonePurchase(navController = rememberNavController())
    }
}