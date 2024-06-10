package com.example.furniture.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.furniture.R
import com.example.furniture.ui.theme.AppTheme


data class RatingType(
    val star: Int,
    val comment: String = ""
)

@Composable
fun ModalRating(
    isVisible: Boolean,
    onCancel: () -> Unit,
    onConfirm: (i: RatingType) -> Unit,

    ) {
    val stars = listOf(1, 2, 3, 4, 5)
    val starSelected = remember { mutableStateListOf<Int>() }
    var comment by remember {
        mutableStateOf("")
    }
    if (isVisible) {
        Dialog(onDismissRequest = { }) {
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
                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Text(
                            text = "Rating & review",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(25.dp)
                                .clickable {
                                    onCancel()
                                }
                        )
                    }

                    Row {
                        stars.mapIndexed { index, item ->
                            Image(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(horizontal = 2.dp)
                                    .size(25.dp)
                                    .clickable {
                                        starSelected.clear()
                                        val starsTemp = arrayListOf<Int>()
                                        for (i in 0..index) {
                                            starsTemp.add(i)
                                        }
                                        starSelected.addAll(starsTemp)

                                    },
                                colorFilter = ColorFilter.tint(
                                    if (index in starSelected) {
                                        Color(0xFFFAC722)
                                    } else {
                                        Color.Gray
                                    }
                                )
                            )
                        }
                    }
                    TextField(
                        modifier = Modifier.padding(top = 20.dp),
                        placeholder = {
                            Text(
                                text = "Add additional comments",
                                style = TextStyle(color = Color.Gray)
                            )
                        },
                        singleLine = true,
                        value = comment, onValueChange = {
                            comment = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color(0xFFF2F2F2),
                            unfocusedContainerColor = Color(0xFFF2F2F2)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onConfirm(RatingType(starSelected.size, comment = comment))
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                        )
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ModalRatingPreview() {
    AppTheme {
        ModalRating(isVisible = true, onConfirm = {}, onCancel = {})
    }
}