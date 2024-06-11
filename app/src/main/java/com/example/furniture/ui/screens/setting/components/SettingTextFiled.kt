package com.example.furniture.ui.screens.setting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.furniture.R

@Composable
fun SettingTextField(
    title: String?,
    isPass: Boolean,
    valueInitial: String,
    labelTextField: String,
    onConfirm: (i: String) -> Unit,
) {
    var value by remember {
        mutableStateOf(valueInitial)
    }
    var enableEditor by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        if (title != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title)
                Image(
                    painter = painterResource(id = if (!enableEditor) R.drawable.pen_icon else R.drawable.tick),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {

                            if (enableEditor) {
                                onConfirm(value)
                            }
                            enableEditor = !enableEditor
                        },
                    colorFilter = ColorFilter.tint(if (enableEditor) Color(0xFF46E966) else Color.Black)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(15.dp)
        ) {
            Text(text = labelTextField)
            TextField(
                enabled = enableEditor,
                value = value,
                onValueChange = {
                    value = it
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                visualTransformation = if (isPass)
                    PasswordVisualTransformation()
                else VisualTransformation.None
            )
        }
    }
}