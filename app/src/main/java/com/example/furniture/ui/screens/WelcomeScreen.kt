package com.example.furniture.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.ImageBackground
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.data.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    val image: Int = R.drawable.background;

    ImageBackground(
        image = image,
        modifier = Modifier.fillMaxSize(),
        imageScale = ContentScale.FillBounds,
        modifierContainer = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "MAKE YOUR", style = AppTheme.appTypography.mediumTitle
                )
                Text(
                    text = "HOME BEAUTIFUL", style = AppTheme.appTypography.largeTitle
                )
            }
            Text(
                text = "The  best simple  " +
                        "place  where  you  discover " +
                        " most wonderful furniture " +
                        " and  make  your  home  beautiful",
                modifier = Modifier
                    .padding(10.dp)
                    .width(300.dp),
                style = AppTheme.appTypography.subTitle
            )

            Button(
                onClick = {
                    navHostController.navigate(NavigationUtils.login)

                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier.padding(top = 100.dp)
            ) {
                Text(
                    text = "Get started",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                    )
                )
            }

        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomePreview() {
    AppTheme {
        val navHostController = rememberNavController();
        WelcomeScreen(navHostController = navHostController)
    }
}