package com.example.furniture.components

import android.util.Log
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Visibility
import com.example.furniture.helper.Console
import com.example.furniture.ui.theme.AppTheme

@Composable
fun DialogMessage(
    visibility: Boolean,
    onClose: () -> Unit,
    title: String,
    message: String,
    titleColor: Color?,
    messageColor: Color?,

    ) {
    if (visibility) {

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
                    .background(Color.White)
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
                            color = titleColor ?: Color.Black,
                            fontSize = 18.sp
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
                            color = messageColor ?: Color.Black,
                            fontSize = 14.sp
                        )
                    )
                }

                Button(
                    modifier = Modifier.padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick =onClose, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        )
                ) {
                    Text(text = "Đóng")
                }
            }
        }
    }
    }else{
        Box{}
    }

}

@Composable
fun test () {
    ConstraintLayout(modifier = Modifier) {

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DialogMessagePreview() {
    AppTheme {
        var dialogMessageVisible by remember {
            mutableStateOf(true)
        }
        DialogMessage(
            visibility = true,
            onClose = {
                dialogMessageVisible = !dialogMessageVisible
            },
            title = "Thông báo",
            message = "Không thể đăng nhập lúc này, tài khoản hoặc mật khẩu không chính xác.",
            titleColor = Color.Red,
            messageColor = Color.White
        )
    }
}