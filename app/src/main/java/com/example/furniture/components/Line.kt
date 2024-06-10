package com.example.furniture.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Line() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(2.dp)
        .background(Color(0xFFF2F2F2)))
}