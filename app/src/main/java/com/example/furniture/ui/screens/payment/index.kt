package com.example.furniture.ui.screens.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.furniture.R
import com.example.furniture.components.Header

@Composable
fun Payment(navHostController: NavController) {
    Column {
        Header(
            iconLeft = R.drawable.left,
            iconRight = null,
            contentCenter = { /*TODO*/ },
            sizeIconLeft = 30.dp,
            sizeIconRight = null,
            iconLeftPress = { /*TODO*/ },
            iconRightPress = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )
    }
}