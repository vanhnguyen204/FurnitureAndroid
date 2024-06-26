package com.example.furniture

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.furniture.ui.navigators.NavigationComponent
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.ui.theme.FurnitureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FurnitureTheme {
              AppTheme {
                 NavigationComponent()
              }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    val image: Int = R.drawable.background;
    AppTheme {

    }
}