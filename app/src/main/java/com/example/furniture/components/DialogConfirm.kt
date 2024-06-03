package com.example.furniture.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun DialogConfirm(
    visible: Boolean,
    title: String,
    message: String,
    titleColor: Color = Color.Red,
    messageColor: Color = Color.White,
    buttonCancelColor: Color = Color.Gray,
    buttonConfirmColor: Color = Color.Red,
    backgroundColor: Color = AppTheme.appColors.appBrown,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onCancel) {
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
                        .background(backgroundColor)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center, modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Text(
                            text = title, style = TextStyle(
                                color = titleColor,
                                fontSize = 24.sp
                            )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = message, style = TextStyle(
                                color = messageColor,
                                fontSize = 16.sp
                            )
                        )
                    }

                   Row(modifier = Modifier.fillMaxWidth()) {
                       Button(
                           modifier = Modifier
                               .padding(top = 20.dp)
                               .weight(1f),
                           shape = RoundedCornerShape(10.dp),
                           onClick = onCancel, colors = ButtonDefaults.buttonColors(
                               containerColor = buttonCancelColor,

                               )
                       ) {
                           Text(text = "Huỷ")
                       }
                       Spacer(modifier = Modifier.width(10.dp))
                       Button(
                           modifier = Modifier
                               .padding(top = 20.dp)
                               .weight(1f),
                           shape = RoundedCornerShape(10.dp),
                           onClick = onConfirm,
                           colors = ButtonDefaults.buttonColors(
                               containerColor = buttonConfirmColor,

                               )
                       ) {
                           Text(text = "Xác nhận")
                       }
                   }
                }
            }
        }

    } else {
        Box {}
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DialogConfirmPreview() {
    AppTheme {
        var dialogMessageVisible by remember {
            mutableStateOf(true)
        }
        DialogConfirm(
            visible = true,
            onConfirm = {
                dialogMessageVisible = !dialogMessageVisible
            },
            title = "Cảnh báo",
            message = "Bạn có chắc muốn huỷ yêu thích sản phẩm này không.",
            titleColor = Color.Red,
            messageColor = Color.White,
            onCancel = {}
        )
    }
}